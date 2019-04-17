package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsLabelManageEntity;

import java.util.Map;

public interface BbsLabelManageService extends IService<BbsLabelManageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
