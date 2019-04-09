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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;
import com.jfsoft.bbs.service.BbsGameGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
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
@RequestMapping("/game/rank")
public class GameGradeController extends AbstractController {

    @Autowired
    private BbsGameGradeService bbsGameGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(Integer gameId) {
        List<BbsGameEntity> rankList = bbsGameGradeService.getRankList(gameId);
        return R.ok().put("data", rankList);
    }

    /**
     * 列表
     */
    @RequestMapping("/all")
    public R allList() {
        List<BbsGameGradeEntity> sumRankList = bbsGameGradeService.getSumRankList();
        return R.ok().put("data", sumRankList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsGameGradeEntity bbsGradeGame = bbsGameGradeService.selectById(id);
        return R.ok().put("data", bbsGradeGame);
    }

    /**
     * 保存
     */
    @RequestMapping("/update")
    public R save(@RequestBody BbsGameGradeEntity bbsGameGradeEntity) {

        bbsGameGradeEntity.setInitTime(new Date());
        EntityWrapper<BbsGameGradeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", bbsGameGradeEntity.getUserId());
        wrapper.eq("game_id", bbsGameGradeEntity.getGameId());
        BbsGameGradeEntity gameGradeEntity = bbsGameGradeService.selectOne(wrapper);
        if (gameGradeEntity == null) {
            bbsGameGradeService.insert(bbsGameGradeEntity);
        } else {
            if (bbsGameGradeEntity.getGrade() > gameGradeEntity.getGrade()) {
                bbsGameGradeService.update(bbsGameGradeEntity, wrapper);
            }
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsGameGradeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}