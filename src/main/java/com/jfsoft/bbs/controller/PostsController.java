package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsLabelEntity;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsLabelService;
import com.jfsoft.bbs.service.BbsLogService;
import com.jfsoft.bbs.service.BbsPostsService;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@RestController
@RequestMapping("/posts")
public class PostsController extends AbstractController {

    @Autowired
    private BbsPostsService bbsPostsService;

    @Autowired
	private BbsLogService bbsLogService;

    /**
     * 首页列表查询
     *
     * @param currPage
     * @param pageSize
     * @param labelId  板块ID
     * @param sortType 排序：0 时间， 1 热度
     * @param postType 查询：0 所有， 1 未结， 2 已结， 3 精华
     * @return
     */
    @RequestMapping("/list")
    public R list(Integer currPage, Integer pageSize, Integer sortType, Integer postType, Integer labelId, Integer tagId,
                  String beginTime, String endTime) {
        List<BbsPostsEntity> list = bbsPostsService.getList(currPage, pageSize, sortType, postType, labelId, tagId, beginTime, endTime);
        return R.ok().put("data", list);
    }


    /**
     * 个人中心发布文章列表
     * * @return
     */
    @RequestMapping("/personList")
    public R getPersonList(Integer userId) {
        EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
        if (userId == null) {
            wrapper.eq("user_id", getUserId());
        } else {
            wrapper.eq("user_id", userId);
        }
        wrapper.eq("is_del", false);
        List<BbsPostsEntity> list = bbsPostsService.selectList(wrapper);
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
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("userId", getUserId());
        BbsPostsEntity bbsPosts = bbsPostsService.getPostByID(params);
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsPostsEntity bbsPosts) {
        Integer postId = bbsPosts.getId();
		BbsUserEntity user = getUser();
		bbsPosts.setInitTime(new Date());
        if (postId == null) {
            // 新增
            bbsPosts.setUserId(user.getId());
            // 帖子显示发帖时的用户姓名
			bbsPosts.setAuthor(user.getUsername());
            bbsPostsService.insert(bbsPosts);

        } else {
            bbsPostsService.updateById(bbsPosts);

        }
        // 发帖钻石记录
		if (bbsPosts.getRewardGrade() > 0) {
			BbsLogEntity bbslog = new BbsLogEntity();
			bbslog.setInitTime(new Date());
			bbslog.setUserId(getUserId());
			bbslog.setLogType(1);
			bbslog.setRemarks("发帖悬赏 " + bbsPosts.getRewardGrade() + " 钻石");
			bbsLogService.insert(bbslog);

		}
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsPostsEntity bbsPosts) {

		BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(bbsPosts.getId());
		bbsPostsService.updateById(bbsPosts);
		// 发帖钻石记录
		if (bbsPosts.getRewardGrade() > 0) {
			BbsLogEntity bbslog = new BbsLogEntity();
			bbslog.setInitTime(new Date());
			bbslog.setUserId(getUserId());
			bbslog.setLogType(1);
			bbslog.setRemarks("帖子" + bbsPostsEntity.getTitle()
					+ " 修改钻石悬赏，由 " + bbsPostsEntity.getRewardGrade()
					+ "修改为 " + bbsPosts.getRewardGrade() + " 钻石");
			bbsLogService.insert(bbslog);
		}
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 查询置顶
     *
     * @return
     */
    @RequestMapping("/collection")
    public R collection() {
        List<BbsPostsEntity> collectList = bbsPostsService.getPostByCollection(getUserId());
        return R.ok().put("data", collectList);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        bbsPostsEntity.setDel(true);
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }

    @RequestMapping("/good/{id}")
    public R good(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        Boolean flag = bbsPostsEntity.getGood();
        if (flag) {
            bbsPostsEntity.setGood(false);
        } else {
            bbsPostsEntity.setGood(true);
        }
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }

    @RequestMapping("/top/{id}")
    public R top(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        Boolean flag = bbsPostsEntity.getTop();
        if (flag) {
            bbsPostsEntity.setTop(false);
        } else {
            bbsPostsEntity.setTop(true);
        }
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }
}
