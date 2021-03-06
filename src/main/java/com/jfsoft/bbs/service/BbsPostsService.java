package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsPostsEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
public interface BbsPostsService extends IService<BbsPostsEntity> {

    PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsPostsEntity> wrapper);

    List<BbsPostsEntity> getList(Integer currPage, Integer pageSize, Integer sortType, Integer postType,
                                 Integer labelId, Integer tagId, String beginTime, String endTime);

    List<BbsPostsEntity> getTopList();

    BbsPostsEntity getPostByID(Map<String, Object> params);

    List<BbsPostsEntity> getPostByCollection(Integer userId);

    List<BbsPostsEntity> getListByReplyTime(Map<String, Object> page);

    List<BbsPostsEntity> getPostsList(Integer pageIndex, Integer pageSize, Integer sortType, Integer tagId, Integer labelId);

    BbsPostsEntity getPostByReplyId(Integer replyId);

    int getTodayCount();

    int getYesterdayCount();

    Integer getPostCount(Integer id);

    /**
     * 查询 label 下最新回帖时间
     */
    Date getLastReplyDate(Integer id);
}

