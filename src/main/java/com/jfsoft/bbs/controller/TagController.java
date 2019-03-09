package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsTagEntity;
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;
import com.jfsoft.bbs.service.BbsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private BbsTagService bbsTagService;

	/**
	 * 列表z
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = bbsTagService.queryPage(params);

		return R.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		BbsTagEntity bbsTag = bbsTagService.selectById(id);

		return R.ok().put("bbsTag", bbsTag);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BbsTagEntity bbsTagEntity){
		bbsTagService.insert(bbsTagEntity);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BbsTagEntity bbsTagEntity){

		bbsTagService.updateAllColumnById(bbsTagEntity);//全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		bbsTagService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}
}
