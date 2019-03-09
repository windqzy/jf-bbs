/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: VoteOptionController
 * Author:   Administrator
 * Date:     2019/3/7 13:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsVoteOptionEntity;
import com.jfsoft.bbs.service.BbsVoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/vote/record")
public class VoteOptionController extends AbstractController {

    @Autowired
    private BbsVoteOptionService bbsVoteOptionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsVoteOptionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/{postId}")
    public R getOptions(@PathVariable("postId") Integer postId){
        Map<String, Object> param = new HashMap<>();
        param.put("userId", getUserId());
        param.put("postId", postId);
        List<BbsVoteOptionEntity> voteOptionByPostId = bbsVoteOptionService.getVoteOptionByPostId(param);
        return R.ok().put("data", voteOptionByPostId);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsVoteOptionEntity bbsVoteOption = bbsVoteOptionService.selectById(id);

        return R.ok().put("bbsVoteOption", bbsVoteOption);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsVoteOptionEntity bbsVoteOptionEntity){
        bbsVoteOptionService.insert(bbsVoteOptionEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsVoteOptionEntity bbsVoteOptionEntity){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsVoteOptionService.updateAllColumnById(bbsVoteOptionEntity);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsVoteOptionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}