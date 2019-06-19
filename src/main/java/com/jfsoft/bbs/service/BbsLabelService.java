package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsLabelEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 21:02:32
 */
public interface BbsLabelService extends IService<BbsLabelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BbsLabelEntity> getLabelByUserId(Map<String, Object> params);

    List<BbsLabelEntity> getLabelById(Map<String, Object> params);
}

