package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGameEntity;

import java.util.Map;

public interface BbsGameService extends IService<BbsGameEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
