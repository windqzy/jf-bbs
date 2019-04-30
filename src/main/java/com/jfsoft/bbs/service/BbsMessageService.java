package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsMessageEntity;

import java.util.Map;

public interface BbsMessageService extends IService<BbsMessageEntity> {

    PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsMessageEntity> wrapper);

    Integer addMsg(Integer msgType, String msgContent, Integer postId, Integer sendUserID, Integer userId, Boolean isUse);

}
