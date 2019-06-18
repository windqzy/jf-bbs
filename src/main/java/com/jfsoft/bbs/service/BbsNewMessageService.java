package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsNewMessageEntity;
import com.jfsoft.bbs.form.MessageVo;

import java.util.List;
import java.util.Map;

public interface BbsNewMessageService extends IService<BbsNewMessageEntity> {

    PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsNewMessageEntity> wrapper);

    List<MessageVo> querySelfMessage(Integer userId);

}
