package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsLogEntity;

import java.util.Map;

public interface BbsLogService extends IService<BbsLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
