package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsUpEntity;

import java.util.Map;

/**
 * 点赞记录表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-24 21:38:33
 */
public interface BbsUpService extends IService<BbsUpEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

