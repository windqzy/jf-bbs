package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.*;
import com.jfsoft.bbs.service.BbsGradeService;
import com.jfsoft.bbs.service.BbsLogService;
import com.jfsoft.bbs.service.BbsUserService;
import com.jfsoft.bbs.service.BbsVestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("vest")
public class BbsVestController extends AbstractController {

	@Autowired
	private BbsVestService bbsVestService;

	@Autowired
	private BbsUserService bbsUserService;

	@Autowired
	private BbsGradeService bbsGradeService;

	@Autowired
	private BbsLogService bbsLogService;


	@RequestMapping("/list")
	public R getVestList() {
		Integer userId = getUserId();
		EntityWrapper<BbsVestEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("user_id", userId);
		List<BbsVestEntity> vestEntities = bbsVestService.selectList(wrapper);
		return R.ok().put("data", vestEntities);
	}

	@RequestMapping("/add")
	public R addVest(String vest) {
		// 验证是否重名
		EntityWrapper<BbsVestEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("vest", vest);
		List<BbsVestEntity> vestEntities = bbsVestService.selectList(wrapper);
		if (!vestEntities.isEmpty()) {
			return R.error("添加马甲失败! 该马甲已存在");
		}
		// 不重名，验证钻石是否充足
		EntityWrapper<BbsGradeEntity> wrapper2 = new EntityWrapper<>();
		wrapper2.eq("user_id", getUserId());
		BbsGradeEntity gradeEntity = bbsGradeService.selectOne(wrapper2);
		if (gradeEntity.getGrade() < 200) {
			return R.error("添加马甲失败! 钻石不足");
		}
		// 条件满足，添加马甲
		BbsVestEntity bbsVestEntity = new BbsVestEntity();
		bbsVestEntity.setInitTime(new Date());
		bbsVestEntity.setUserId(getUserId());
		bbsVestEntity.setVest(vest);
		boolean insert = bbsVestService.insert(bbsVestEntity);
		// 扣钻石
		BbsGradeEntity bbsGradeEntity = new BbsGradeEntity();
		bbsGradeEntity.setId(gradeEntity.getId());
		bbsGradeEntity.setGrade(gradeEntity.getGrade() - 200);
		bbsGradeService.updateById(bbsGradeEntity);
		// 钻石记录
		BbsLogEntity logEntity = new BbsLogEntity();
		logEntity.setUserId(getUserId());
		logEntity.setLogType(1);
		logEntity.setInitTime(new Date());
		logEntity.setRemarks("购买马甲，花费 200 个钻石");
		bbsLogService.insert(logEntity);

		return R.ok("添加马甲成功!");
	}

	@RequestMapping("/rename/{id}")
	public R updateUsername(@PathVariable("id") Integer id) {
		BbsUserEntity bbsUserEntity = new BbsUserEntity();
		bbsUserEntity.setId(getUserId());
		bbsUserEntity.setUsername(bbsVestService.selectById(id).getVest());
		bbsUserService.updateById(bbsUserEntity);
		return R.ok("切换马甲成功!");
	}


	@RequestMapping("/update")
	public R update(BbsVestEntity bbsVestEntity) {
		bbsVestService.updateById(bbsVestEntity);
		return R.ok();
	}

}
