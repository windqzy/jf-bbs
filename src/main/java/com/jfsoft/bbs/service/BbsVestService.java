package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsVestEntity;

import java.util.Map;

public interface BbsVestService extends IService<BbsVestEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
