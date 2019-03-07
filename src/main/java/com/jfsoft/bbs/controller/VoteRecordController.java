/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: VoteRecordController
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
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;
import com.jfsoft.bbs.service.BbsVoteRecordService;
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
@RequestMapping("/vote/option")
public class VoteRecordController extends AbstractController {

    @Autowired
    private BbsVoteRecordService bbsVoteRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsVoteRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsVoteRecordEntity bbsVoteRecord = bbsVoteRecordService.selectById(id);

        return R.ok().put("bbsVoteRecord", bbsVoteRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsVoteRecordEntity bbsVoteRecordEntity){
        bbsVoteRecordService.insert(bbsVoteRecordEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsVoteRecordEntity bbsVoteRecordEntity){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsVoteRecordService.updateAllColumnById(bbsVoteRecordEntity);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsVoteRecordService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}