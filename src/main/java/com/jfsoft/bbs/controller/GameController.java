/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GameController
 * Author:   Administrator
 * Date:     2019/4/3 15:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;
import com.jfsoft.bbs.service.BbsGameGradeService;
import com.jfsoft.bbs.service.BbsGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("/game")
public class GameController extends AbstractController {

    @Autowired
    private BbsGameService bbsGameService;

    @Autowired
    private BbsGameGradeService bbsGameGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list() {
        List<BbsGameEntity> bbsGameEntities = bbsGameService.selectList(new EntityWrapper<>());
        bbsGameEntities.forEach(game -> {
            BbsGameGradeEntity firstGrade = bbsGameGradeService.getFirstGrade(game.getId());
            if (firstGrade != null) {
                game.setFirst(firstGrade.getUserName());
            } else {
                game.setFirst(null);
            }
        });
        return R.ok().put("data", bbsGameEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsGameEntity bbsGame = bbsGameService.selectById(id);
        return R.ok().put("bbsGame", bbsGame);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsGameEntity bbsGameEntity){
        bbsGameService.insert(bbsGameEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsGameEntity bbsGameEntity){
//        ValidatorUtils.validateEntity(bbsGradeRule);
        bbsGameService.updateAllColumnById(bbsGameEntity);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsGameService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}