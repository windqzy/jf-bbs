package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.entity.BbsRewardEntity;
import com.jfsoft.bbs.service.BbsGradeService;
import com.jfsoft.bbs.service.BbsLogService;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户积分表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@RestController
@RequestMapping("/grade")
public class GradeController extends AbstractController {

	@Autowired
	private BbsGradeService bbsGradeService;

	@Autowired
	private BbsLogService bbsLogService;

	@Autowired
	private BbsUserService bbsUserService;


	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = bbsGradeService.queryPage(params);
		return R.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info")
	public R info(Integer userId) {
		EntityWrapper<BbsGradeEntity> wrapper = new EntityWrapper<>();
		String unionId;
		if (userId == null) {
			unionId = getUnionId();
		} else {
			unionId = bbsUserService.selectById(userId).getUnionId();
		}
		wrapper.eq("union_id", unionId);
		BbsGradeEntity bbsGrade = bbsGradeService.selectOne(wrapper);
		return R.ok().put("data", bbsGrade);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BbsGradeEntity bbsGrade) {
		bbsGradeService.insert(bbsGrade);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody Integer newGrade) {
		//ValidatorUtils.validateEntity(bbsGrade);
		EntityWrapper<BbsGradeEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("union_id", getUnionId());
		BbsGradeEntity bbsGrade = bbsGradeService.selectOne(wrapper);
		bbsGrade.setGrade(newGrade);
		//更新该用户的积分
		bbsGradeService.updateById(bbsGrade);
		return R.ok().put("data", bbsGrade);
	}

	@RequestMapping("/reward")
	public R reward(Integer fromId, Integer toId, Integer grade) {

		// 打赏者
		String fromUnionId = bbsUserService.selectById(fromId).getUnionId();
		EntityWrapper<BbsGradeEntity> fromWrapper = new EntityWrapper<>();
		fromWrapper.eq("union_id", fromUnionId);
		BbsGradeEntity fromGradeEntity = bbsGradeService.selectOne(fromWrapper);
		Integer fromGrade = fromGradeEntity.getGrade();
		fromGradeEntity.setGrade(fromGrade - grade);
		bbsGradeService.updateById(fromGradeEntity);

		BbsLogEntity fromLog = new BbsLogEntity();
		fromLog.setInitTime(new Date());
		fromLog.setLogType(1);
		fromLog.setUserId(fromId);
		fromLog.setUnionId(fromUnionId);
		fromLog.setRemarks("打赏文章花费" + grade + "钻石");
		bbsLogService.insert(fromLog);

		// 被打赏者
		String toUnionId = bbsUserService.selectById(toId).getUnionId();
		EntityWrapper<BbsGradeEntity> toWrapper = new EntityWrapper<>();
		toWrapper.eq("union_id", toUnionId);
		BbsGradeEntity toGradeEntity = bbsGradeService.selectOne(toWrapper);
		Integer toGrade = toGradeEntity.getGrade();
		toGradeEntity.setGrade(toGrade + grade);
		bbsGradeService.updateById(toGradeEntity);

		// 记录日志
		BbsLogEntity toLog = new BbsLogEntity();
		toLog.setInitTime(new Date());
		toLog.setLogType(1);
		toLog.setUserId(fromId);
		toLog.setUnionId(toUnionId);
		toLog.setRemarks("文章被打赏，获得" + grade + "钻石");
		bbsLogService.insert(toLog);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		bbsGradeService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

	/**
	 * 根据postsID查询打赏榜前三名
	 * @author haoxl
	 * @param postsId
	 * @return
	 */
	@RequestMapping("/finTopThree")
	public R finTopThree(String postsId){
		List<BbsRewardEntity> list = bbsGradeService.finTopThree(postsId);
		return R.ok().put("data",list);
	}
}
