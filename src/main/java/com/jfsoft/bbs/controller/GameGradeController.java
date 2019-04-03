/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GameGradeController
 * Author:   Administrator
 * Date:     2019/4/3 15:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;
import com.jfsoft.bbs.service.BbsGameGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("/gradeGrade")
public class GameGradeController extends AbstractController {

    @Autowired
    private BbsGameGradeService bbsGameGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsGameGradeService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsGameGradeEntity bbsGradeGame = bbsGameGradeService.selectById(id);
        return R.ok().put("bbsGradeGame", bbsGradeGame);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsGameGradeEntity bbsGameGradeEntity){
        bbsGameGradeService.insert(bbsGameGradeEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsGameGradeEntity bbsGameGradeEntity){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsGameGradeService.updateAllColumnById(bbsGameGradeEntity);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsGameGradeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}