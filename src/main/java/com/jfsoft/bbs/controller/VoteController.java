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
import com.jfsoft.bbs.common.utils.DateUtil;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsVoteEntity;
import com.jfsoft.bbs.entity.BbsVoteOptionEntity;
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;
import com.jfsoft.bbs.service.BbsVoteOptionService;
import com.jfsoft.bbs.service.BbsVoteRecordService;
import com.jfsoft.bbs.service.BbsVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private BbsVoteOptionService bbsVoteOptionService;

    @Autowired
    private BbsVoteRecordService bbsVoteRecordService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsVoteService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 获取投票信息
     */
    @RequestMapping("/{postId}")
    public R getVoteByPostId(@PathVariable("postId") Integer postId){
        EntityWrapper<BbsVoteEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("post_id", postId);
        // 根据postId得到投票信息
        BbsVoteEntity bbsVote = bbsVoteService.selectOne(wrapper);
        if (bbsVote != null) {
            Map<String, Object> param = new HashMap<>();
            param.put("userId", getUserId());
            param.put("postId", postId);
            // 根据当前用户和帖子ID查出选项并查出当前用户选择的选项
            List<BbsVoteOptionEntity> optionList = bbsVoteOptionService.getVoteOptionByPostId(param);
            bbsVote.setOptionList(optionList);
        }
        return R.ok().put("data", bbsVote);
    }

    /**
     * 添加投票信息
     */
    @RequestMapping("/add")
    public R insertVote(@RequestBody BbsVoteEntity bbsVote){
        // 将信息补全
        if (bbsVote.getMaxSel() > 1) {
            bbsVote.setMultiple(true);
        }
        // 查询是修改还是新增
        EntityWrapper<BbsVoteEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("post_id", bbsVote.getPostId());
        BbsVoteEntity voteEntity = bbsVoteService.selectOne(wrapper);
        if (voteEntity == null) {
            // 新增
            bbsVote.setInitTime(new Date());
            bbsVote.setStartTime(new Date());
            bbsVoteService.insert(bbsVote);
            // 获取选项
            bbsVote.getOptionList().forEach(option -> {
                option.setInitTime(new Date());
                option.setVoteId(bbsVote.getId());
            });
            // 将选项插入选项表
            bbsVoteOptionService.insertBatch(bbsVote.getOptionList());
        } else {
            // 修改
            bbsVote.setInitTime(voteEntity.getInitTime());
            bbsVote.setStartTime(voteEntity.getStartTime());
            bbsVoteService.updateById(bbsVote);
            // 获取选项
            bbsVote.getOptionList().forEach(option -> {
                option.setInitTime(new Date());
                option.setVoteId(bbsVote.getId());
            });

        }

        return R.ok().put("data", "");
    }


    /**
     * 投票
     */
    @RequestMapping("/record")
    public R userVote(Integer voteId, Integer[] options){
        // 投票时先判断是否在投票时间内
        BbsVoteEntity voteEntity = bbsVoteService.selectById(voteId);
        if (DateUtil.compare(new Date(), voteEntity.getEndTime()) <= 0) {

            // 如果未结束，添加投票记录投票
            List<BbsVoteRecordEntity> voteRecordList = new ArrayList<>();
            List<BbsVoteOptionEntity> voteOptionList = new ArrayList<>();
            for (int i = 0; i < options.length; i++) {
                BbsVoteRecordEntity voteRecord = new BbsVoteRecordEntity();
                voteRecord.setInitTime(new Date());
                voteRecord.setOptionId(options[i]);
                voteRecord.setUserId(getUserId());
                voteRecordList.add(voteRecord);
                // 修改被投项目的投票数
                BbsVoteOptionEntity optionEntity = bbsVoteOptionService.selectById(options[i]);
                optionEntity.setOptionCount(optionEntity.getOptionCount() + 1);
                voteOptionList.add(optionEntity);
            }
            Boolean insertBatch = bbsVoteRecordService.insertBatch(voteRecordList);
            boolean updateBatchById = bbsVoteOptionService.updateBatchById(voteOptionList);

            if (insertBatch) {
                return R.ok().put("data", "投票成功");
            }  else {
                return R.ok().put("data", "投票失败");
            }

        } else {
            return R.ok().put("data", "投票失败,投票已结束");
        }
    }

    /**
     * 判断用户是否投过票
     */
    @RequestMapping("/isVote/{postId}")
    public R isVote(@PathVariable("postId") Integer postId){
        Map<String, Object> param = new HashMap<>();
        param.put("userId", getUserId());
        param.put("postId", postId);
        BbsVoteRecordEntity record = bbsVoteRecordService.getRecord(param);
        if (record == null) {
            return R.ok().put("data", false);
        } else {
            return R.ok().put("data", true);
        }
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