package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsPostsEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
public interface BbsPostsService extends IService<BbsPostsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

