package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsLabelEntity;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsLabelService;
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
    private BbsUserService bbsUserService;

    @Autowired
    private BbsLabelService bbsLabelService;

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
    public R list(Integer currPage, Integer pageSize, Integer sortType, Integer postType, Integer labelId,
                  String beginTime, String endTime) {
        List<BbsPostsEntity> list = bbsPostsService.getList(currPage, pageSize, sortType, postType, labelId, beginTime, endTime);
        return R.ok().put("data", list);
    }


    /**
     * 个人中心发布文章列表
     * * @return
     */
    @RequestMapping("/personList")
    public R getPersonList() {
        EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", getUserId());
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
        Integer userId = getUserId();
        bbsPosts.setUserId(userId);
        bbsPosts.setInitTime(new Date());
        //获取该用户信息
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);
        bbsPosts.setAuthor(bbsUser.getUsername());
        bbsPosts.setIcon(bbsUser.getIcon());
        //获取板块信息
        BbsLabelEntity bbsLabel = bbsLabelService.selectById(bbsPosts.getLabelId());
        bbsPosts.setLabelName(bbsLabel.getName());

        bbsPostsService.insert(bbsPosts);
        return R.ok().put("data", bbsPosts);
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
