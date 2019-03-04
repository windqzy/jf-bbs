package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsCollectionEntity;

import java.util.Map;

public interface BbsCollectionService extends IService<BbsCollectionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
