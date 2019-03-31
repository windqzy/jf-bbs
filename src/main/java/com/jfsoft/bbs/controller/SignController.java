package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.DateUtil;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsGradeRuleService;
import com.jfsoft.bbs.service.BbsGradeService;
import com.jfsoft.bbs.service.BbsLogService;
import com.jfsoft.bbs.service.BbsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * 签到表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@RestController
@RequestMapping("/sign")
public class SignController extends AbstractController {

	@Autowired
	private BbsSignService bbsSignService;

	@Autowired
	private BbsGradeRuleService bbsGradeRuleService;

	@Autowired
	private BbsGradeService bbsGradeService;

	@Autowired
	private BbsLogService bbsLogService;

	/**
	 * 列表
	 */
	@RequestMapping("/list/{listType}") // listType  1表示最新签到,2表示今日签到最快,3表示连续签到天数
	public R list(@PathVariable("listType") Integer listType) {
		Map<String, Object> params = new HashMap<>();
		if (listType != 3) {
			params.put("today", "today");
		}
		params.put("listType", listType);
		List<BbsSignEntity> signList = bbsSignService.getSignList(params);
		return R.ok().put("data", signList);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		BbsSignEntity bbsSign = bbsSignService.selectById(id);
		return R.ok().put("data", bbsSign);
	}

	/**
	 * 查询签到表信息
	 *
	 * @return
	 */
	@RequestMapping("/count")
	public R selectCount() {
		Integer userId = getUserId();
		EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("user_id", userId);
		BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);
		return R.ok().put("data", bbsSign);
	}


	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save() {
		// 提示信息
		String msg = "签到失败";
		Integer grade = 0;
		Integer userId = getUserId();
		// 获取当前方法执行时的时间戳
		Date initTime = new Date();
		// 检查是否有该用户的签到记录
		EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("user_id", userId);
		BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);
		// 检查是否有该用户的积分记录
		EntityWrapper<BbsGradeEntity> wrapperGrade = new EntityWrapper<>();
		wrapperGrade.eq("user_id", userId);
		BbsGradeEntity bbsGrade = bbsGradeService.selectOne(wrapperGrade);
		// 向签到表中存储的数据
		BbsSignEntity newBbsSign = new BbsSignEntity();

		// 查询用户的分数
		EntityWrapper<BbsGradeEntity> gradeWrapper = new EntityWrapper<>();
		gradeWrapper.eq("user_id", userId);
		BbsGradeEntity bbsGradeEntity = bbsGradeService.selectOne(gradeWrapper);

		// 保存签到记录
		BbsLogEntity bbsLog = new BbsLogEntity();
		bbsLog.setInitTime(initTime);
		bbsLog.setLogType(1);
		bbsLog.setUserId(userId);

		// 签到
		if (bbsSign == null) {
			newBbsSign.setInitTime(initTime);
			newBbsSign.setUserId(userId);
			newBbsSign.setSignCount(1);
			BbsGradeEntity gradeEntity = new BbsGradeEntity();
			if (bbsGrade == null) {
				// grade表为空，新增一条记录
				gradeEntity.setInitTime(initTime);
				grade = bbsGradeRuleService.getGradeByRule(1);
				gradeEntity.setGrade(grade);
				gradeEntity.setUserId(userId);
				bbsGradeService.insert(gradeEntity);
			} else {
				// grade表不为空，首次签到+5分
				gradeEntity.setInitTime(initTime);
				grade = bbsGradeRuleService.getGradeByRule(1);
				gradeEntity.setGrade(grade);
				bbsGradeService.update(gradeEntity, wrapperGrade);
			}
			msg = "签到成功";
			// 新增签到记录
			bbsSignService.insert(newBbsSign);
			// 保存签到钻石记录
		} else {
			// 最后签到时间
			Date singTime = bbsSign.getInitTime();
			// 获取当前天数差
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long day = ChronoUnit.DAYS.between(LocalDate.parse(sdf.format(singTime)), LocalDate.parse(sdf.format(initTime)));
			// 当前时间与最后签到时间作比较
			if (day == 1) {
				// 如果间隔一天，则是连续签到
				bbsSign.setInitTime(initTime);
				bbsSign.setSignCount(bbsSign.getSignCount() + 1);
				// 更新新的签到
				bbsSignService.updateById(bbsSign);
				// 查找签到天数应有的分数
				grade = bbsGradeRuleService.getGradeByRule(bbsSign.getSignCount());
				// 更新用户的分数
				int currGrade = bbsGradeEntity.getGrade() + grade;
				bbsGradeEntity.setGrade(currGrade);
				bbsGradeEntity.setInitTime(new Date());
				bbsGradeService.update(bbsGradeEntity, gradeWrapper);
				msg = "连续签到";
			} else if (day > 1) {
				// 断签，直接清零
				bbsSign.setInitTime(initTime);
				bbsSign.setSignCount(1);
				// 更新新的签到记录
				bbsSignService.updateById(bbsSign);
				// 查找签到天数应有的分数
				grade = bbsGradeRuleService.getGradeByRule(1);
				// 更新用户的分数
				int currGrade = bbsGradeEntity.getGrade() + grade;
				bbsGradeEntity.setGrade(currGrade);
				bbsGradeEntity.setInitTime(new Date());
				bbsGradeService.update(bbsGradeEntity, gradeWrapper);
				msg = "您断签了，从头开始";
			}
		}
		// 添加钻石记录日志
		bbsLog.setRemarks("签到增加" + grade + "钻石!");
		bbsLogService.insert(bbsLog);
		return R.ok(msg);
	}


	@RequestMapping("/bool")
	public R isSign() {
		Map<String, Object> map = new HashMap<>();
		Integer userId = getUserId();
		//当前时间
		Date initTime = new Date();
		//检查是否有该用户的签到记录
		EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("user_id", userId);
		BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);

		if (bbsSign == null) {
			map.put("isSign", false);
			map.put("count", 0);
		} else {
			//最后签到时间
			Date lastSingTime = bbsSign.getInitTime();
			int day = DateUtil.getIntervalDays(lastSingTime, initTime);
			if (day == 0) {
				map.put("isSign", true);
			} else {
				map.put("isSign", false);
			}
			map.put("count", bbsSign.getSignCount());
		}
		return R.ok().put("data", map);
	}




	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update() {
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		bbsSignService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

}
