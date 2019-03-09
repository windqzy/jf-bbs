package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsTagEntity;

import java.util.Map;

public interface BbsTagService extends IService<BbsTagEntity> {

	PageUtils queryPage(Map<String, Object> params);

}
