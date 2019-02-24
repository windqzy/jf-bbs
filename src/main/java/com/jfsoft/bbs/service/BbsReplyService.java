package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsReplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
public interface BbsReplyService extends IService<BbsReplyEntity> {

    PageUtils queryPage(Map<String, Object> params);


    List<BbsReplyEntity> getTop(String beginTime, String endTime);

    List<BbsReplyEntity> getReplyByPostid(Integer postId, Integer userId);
}

