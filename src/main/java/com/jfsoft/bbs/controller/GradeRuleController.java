package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGradeRuleEntity;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsGradeRuleService;
import com.jfsoft.bbs.service.BbsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 积分规则表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@RestController
@RequestMapping("/grade/rule")
public class GradeRuleController extends AbstractController {
    @Autowired
    private BbsGradeRuleService bbsGradeRuleService;

    @Autowired
    private BbsSignService bbsSignService;


    /**
     * 信息
     */
    @RequestMapping("/curr")
    public R curr(){
        Integer grade;
        // 查询最大连签
        BbsSignEntity signByUserId = bbsSignService.getSignByUserId(getUserId());
        // 查询应得的分数
        if (signByUserId == null) {
            grade = bbsGradeRuleService.getGradeByRule(1);
        } else {
            grade = bbsGradeRuleService.getGradeByRule(signByUserId.getSignCount());
        }

        return R.ok().put("data", grade);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsGradeRuleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsGradeRuleEntity bbsGradeRule = bbsGradeRuleService.selectById(id);

        return R.ok().put("bbsGradeRule", bbsGradeRule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsGradeRuleEntity bbsGradeRule){
        bbsGradeRuleService.insert(bbsGradeRule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsGradeRuleEntity bbsGradeRule){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsGradeRuleService.updateAllColumnById(bbsGradeRule);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsGradeRuleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
