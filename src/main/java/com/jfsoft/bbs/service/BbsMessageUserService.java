package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsMessageUserEntity;
import com.jfsoft.bbs.entity.BbsNewMessageEntity;

import java.util.Map;

public interface BbsMessageUserService extends IService<BbsMessageUserEntity> {

    PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsMessageUserEntity> wrapper);

}
