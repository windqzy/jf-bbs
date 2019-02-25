package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsSignEntity;

import java.util.Map;

/**
 * 签到表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
public interface BbsSignService extends IService<BbsSignEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

