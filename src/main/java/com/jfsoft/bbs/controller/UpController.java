package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsUpEntity;
import com.jfsoft.bbs.service.BbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 点赞记录表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-24 21:38:33
 */
@RestController
@RequestMapping("/up")
public class UpController extends AbstractController {
	@Autowired
	private BbsUpService bbsUpService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")

	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = bbsUpService.queryPage(params);
		return R.ok().put("page", page);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		BbsUpEntity bbsUp = bbsUpService.selectById(id);
		return R.ok().put("bbsUp", bbsUp);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save/{replyId}")
	public R save(@PathVariable("replyId") Integer replyId) {
		bbsUpService.insertUp(getUserId(), replyId);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update/{replyId}")
	public R update(@PathVariable("replyId") Integer replyId) {
		BbsUpEntity bbsUpEntity = bbsUpService.getUpByRidUid(getUserId(), replyId);
		if (bbsUpEntity == null) {
			bbsUpService.insertUp(getUserId(), replyId);
		} else {
			bbsUpService.updateUp(getUserId(), replyId, bbsUpEntity.getStatus());
		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		bbsUpService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

}
