package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsRewardEntity;

import java.util.Map;

public interface BbsRewardService extends IService<BbsRewardEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
