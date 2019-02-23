package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@RestController
@RequestMapping("/posts")
public class PostsController {

	@Autowired
	private BbsPostsService bbsPostsService;

	/**
	 * 首页列表查询
	 *
	 * @param currPage
	 * @param pageSize
	 * @param sort       排序：0 时间， 1 热度
	 * @param searchType 查询：0 所有， 1 未结， 2 已结， 3 精华
	 * @return
	 */
	@RequestMapping("/list")
	public R list(Integer currPage, Integer pageSize, Integer sort, Integer searchType) {
		List<BbsPostsEntity> list = bbsPostsService.getList(currPage, pageSize, sort, searchType);
		return R.ok().put("data", list);
	}


	/**
	 * 查询置顶
	 *
	 * @return
	 */
	@RequestMapping("/top")
	public R top() {
		List<BbsPostsEntity> topList = bbsPostsService.getTopList();
		return R.ok().put("data", topList);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/detail/{id}")
	public R info(@PathVariable("id") Integer id) {
		BbsPostsEntity bbsPosts = bbsPostsService.getPostByID(id);
		return R.ok().put("data", bbsPosts);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")

	public R save(@RequestBody BbsPostsEntity bbsPosts) {
		bbsPostsService.insert(bbsPosts);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")

	public R update(@RequestBody BbsPostsEntity bbsPosts) {
//        ValidatorUtils.validateEntity(bbsPosts);
//        bbsPostsService.updateAllColumnById(bbsPosts);//全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		bbsPostsService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

}
