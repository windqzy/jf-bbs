package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.entity.BbsReplyEntity;
import com.jfsoft.bbs.form.ReplyForm;
import com.jfsoft.bbs.service.BbsPostsService;
import com.jfsoft.bbs.service.BbsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
@RestController
@RequestMapping("/reply")
public class ReplyController extends AbstractController {
	@Autowired
	private BbsReplyService bbsReplyService;

	@Autowired
	private BbsPostsService bbsPostsService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = bbsReplyService.queryPage(params);
		return R.ok().put("page", page);
	}


	/**
	 * 获取回复周榜
	 *
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/top")
	public R top(String beginTime, String endTime) {
		List<BbsReplyEntity> topList = bbsReplyService.getTop(beginTime, endTime);
		return R.ok().put("data", topList);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id) {
		BbsReplyEntity bbsReply = bbsReplyService.selectById(id);
		return R.ok().put("data", bbsReply);
	}

	/**
	 * 根据postId获取回复
	 */
	@RequestMapping("/list/{postId}")
	public R postInfo(@PathVariable("postId") Integer postId) {
		List<BbsReplyEntity> replyList = bbsReplyService.getReplyByPostid(postId, getUserId());
		return R.ok().put("data", replyList);
	}


	/**
	 * 回帖
	 */
	@PostMapping("/save")
	public R addReply(@RequestBody ReplyForm ReplyForm) {
		BbsReplyEntity bbsReply = new BbsReplyEntity();
		bbsReply.setPostsId(ReplyForm.getPostsId());
		bbsReply.setInitTime(new Date());
		bbsReply.setUserId(getUserId());
		bbsReply.setContent(ReplyForm.getContent());
		// 增加一条评论
		bbsReplyService.insert(bbsReply);
		// 更新post表的评论数
		BbsPostsEntity bbsPosts = bbsPostsService.selectById(ReplyForm.getPostsId());
		int count = bbsPosts.getReplyCount() + 1;
		bbsPosts.setReplyCount(count);
		bbsPostsService.updateById(bbsPosts);
		return R.ok().put("data", "评论成功");
	}

	/**
	 * 修改
	 */
//    @RequestMapping("/update")
//    public R update(@RequestBody BbsReplyEntity bbsReply) {
////        ValidatorUtils.validateEntity(bbsReply);
//        bbsReplyService.updateAllColumnById(bbsReply);//全部更新
//
//        return R.ok();
//    }

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ReplyForm ReplyForm) {
		BbsReplyEntity bbsReply = new BbsReplyEntity();
		bbsReply.setId(ReplyForm.getId());
		bbsReply.setInitTime(new Date());
		bbsReply.setContent(ReplyForm.getContent());
		boolean b = bbsReplyService.updateById(bbsReply);
		return R.ok().put("data", b);
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids) {
		bbsReplyService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

	/**
	 * 根据评论id删除
	 */
	@RequestMapping("/delete/{id}")
	public R delete(@PathVariable("id") Integer id) {
		Boolean flag = bbsReplyService.deleteById(id);
		return R.ok().put("data", flag);
	}


	/**
	 * 获取个人文章的所有回复
	 */
	@RequestMapping("/personReply")
	public R getPersonReply(Integer userId) {

		Map<String, Object> param = new HashMap<String, Object>();
		if (userId == null) {
			param.put("user_id", getUserId());
		} else {
			param.put("user_id", userId);
		}

		List<BbsReplyEntity> list = bbsReplyService.getPersonReplyList(param);
		return R.ok().put("data", list);
	}

}
