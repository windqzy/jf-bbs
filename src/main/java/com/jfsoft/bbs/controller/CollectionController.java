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

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsCollectionEntity;
import com.jfsoft.bbs.service.BbsCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
        BbsCollectionEntity bbsCollection = new BbsCollectionEntity();
        bbsCollection.setPostId(postId);
        bbsCollection.setUserId(getUserId());
        Boolean b = bbsCollectionService.insert(bbsCollection);
        return R.ok().put("data",b);
    }

    /**
     * 修改
     */
    @RequestMapping("/update/{replyId}")
    public R update(@PathVariable("replyId") Integer replyId) {

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsCollectionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}