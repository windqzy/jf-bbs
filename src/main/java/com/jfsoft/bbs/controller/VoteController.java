/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: VoteController
 * Author:   Administrator
 * Date:     2019/3/7 13:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsVoteEntity;
import com.jfsoft.bbs.service.BbsVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/7
 * @since 1.0.0
 */
@RestController
@RequestMapping("/vote")
public class VoteController extends AbstractController {

    @Autowired
    private BbsVoteService bbsVoteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsVoteService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/{postId}")
    public R getVoteByPostId(@PathVariable("postId") Integer postId){
        EntityWrapper<BbsVoteEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("post_id", postId);
        // 根据postId得到投票信息
        BbsVoteEntity bbsVote = bbsVoteService.selectOne(wrapper);

        return R.ok().put("bbsVote", bbsVote);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsVoteEntity bbsVote = bbsVoteService.selectById(id);

        return R.ok().put("bbsVote", bbsVote);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsVoteEntity bbsVoteEntity){
        bbsVoteService.insert(bbsVoteEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsVoteEntity bbsVoteEntity){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsVoteService.updateAllColumnById(bbsVoteEntity);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsVoteService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}