/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CollectionController
 * Author:   Administrator
 * Date:     2019/2/28 16:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsCollectionEntity;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/collection")
public class CollectionController extends AbstractController {

    @Autowired
    private BbsCollectionService bbsCollectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsCollectionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsCollectionEntity bbsCollection = bbsCollectionService.selectById(id);
        return R.ok().put("data", bbsCollection);
    }

    /**
     * 保存
     */
    @RequestMapping("/save/{postId}")
    public R save(@PathVariable("postId") Integer postId) {

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update/{postId}")
    public R update(@PathVariable("postId") Integer postId) {
        EntityWrapper<BbsCollectionEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", getUserId());
        wrapper.eq("post_id", postId);
        // 查找收藏记录是否存在
        BbsCollectionEntity bbsCollection = bbsCollectionService.selectOne(wrapper);
        BbsCollectionEntity newBbsCollection = new BbsCollectionEntity();
        String msg;
        if (bbsCollection == null) {
            newBbsCollection.setPostId(postId);
            newBbsCollection.setUserId(getUserId());
            newBbsCollection.setStatus(true);
            newBbsCollection.setInitTime(new Date());
            bbsCollectionService.insert(newBbsCollection);
            msg = "收藏成功！";
        } else {
            newBbsCollection.setStatus(!bbsCollection.getStatus());
            newBbsCollection.setInitTime(new Date());
            bbsCollectionService.update(newBbsCollection, wrapper);
            if (bbsCollection.getStatus()) {
                msg = "取消收藏成功！";
            } else {
                msg = "收藏成功！";
            }
        }
        return R.ok().put("data", msg);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsCollectionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 查询该帖子是否被登陆者收藏
     *
     * @Author Mjp
     * @Date 17:29 2019/6/19
     * @Param [postId]
     * @Return false 未收藏，true 已收藏
     **/
    @RequestMapping("/isColl/{postId}")
    public R isColl(@PathVariable("postId") Integer postId) {
        Integer userId = getUserId();
        EntityWrapper<BbsCollectionEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("post_id", postId);
        wrapper.eq("status", true);
        BbsCollectionEntity bbsCollectionEntity = bbsCollectionService.selectOne(wrapper);
        if (bbsCollectionEntity == null) {
            return R.ok().put("isColl", "false");
        } else {
            return R.ok().put("isColl", "true");
        }

    }

}