package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.dao.BbsReplyDao;
import com.jfsoft.bbs.entity.BbsPostsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
public interface BbsPostsService extends IService<BbsPostsEntity> {

    PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsPostsEntity> wrapper);

    List<BbsPostsEntity> getList(Integer currPage, Integer pageSize, Integer sort, Integer searchType);

    List<BbsPostsEntity> getTopList();

    BbsPostsEntity getPostByID(Integer ID);
}

