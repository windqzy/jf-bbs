package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
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
     * 列表
     */
    @RequestMapping("/list")
    public R list(){
        Map<String,Object> map= new HashMap<>();
        map.put("currPage", 1);
        map.put("pageSize", 5);
        PageUtils page = bbsPostsService.queryPage(map);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsPostsEntity bbsPosts = bbsPostsService.selectById(id);

        return R.ok().put("bbsPosts", bbsPosts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody BbsPostsEntity bbsPosts){
        bbsPostsService.insert(bbsPosts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody BbsPostsEntity bbsPosts){
//        ValidatorUtils.validateEntity(bbsPosts);
//        bbsPostsService.updateAllColumnById(bbsPosts);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsPostsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
