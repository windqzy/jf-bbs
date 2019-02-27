package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsUserEntity;

import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 14:33:16
 */
public interface BbsUserService extends IService<BbsUserEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

