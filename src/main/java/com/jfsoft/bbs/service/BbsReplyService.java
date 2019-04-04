package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsReplyEntity;

import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
public interface BbsReplyService extends IService<BbsReplyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BbsReplyEntity> getTop(String beginTime, String endTime);

    List<BbsReplyEntity> getReplyByPostid(Integer postId, Integer userId);

    List<BbsReplyEntity> getHotReplyByPostid(Integer postId, Integer userId);

    List<BbsReplyEntity> getPersonReplyList(Map<String, Object> param);

    Integer getAccept(Integer id);

    Integer trueAccept(Integer id);

    Integer getRewardGrade(Integer id);

    Integer upGrade(Integer userId, Integer rewardgrade);

    Integer upEnd(Integer id);
}

