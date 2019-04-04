package com.jfsoft.bbs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.BaseContants;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsPostsDao;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bbsPostsService")
public class BbsPostsServiceImpl extends ServiceImpl<BbsPostsDao, BbsPostsEntity> implements BbsPostsService {

    @Autowired
    BbsPostsDao bbsPostsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsPostsEntity> wrapper) {
        Page<BbsPostsEntity> page = this.selectPage(
                new Query<BbsPostsEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<BbsPostsEntity> getList(Integer currPage, Integer pageSize, Integer sortType, Integer postType,
                                        Integer labelId, Integer tagId, String beginTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("startPage", pageSize * (currPage - 1));
        params.put("endPage", pageSize);
        params.put("labelId", labelId);
        params.put("tagId", tagId);
        if (StringUtils.isNotBlank(beginTime)) {
            params.put("beginTime", beginTime + BaseContants.BEGIN_TIME_EXT);
        }
        if (StringUtils.isNotBlank(endTime)) {
            params.put("endTime", endTime + BaseContants.END_TIME_EXT);
        }
        if (sortType == 0) {
            params.put("order", "init_time");
        } else {
            params.put("order", "reply_count");
        }
        if (postType == 3) {
            params.put("good", true);
        } else if (postType == 2) {
            params.put("end", true);
        } else if (postType == 1) {
            params.put("end", false);
        }
        return bbsPostsDao.getList(params);
    }

    @Override
    public List<BbsPostsEntity> getTopList() {
        Map<String, Object> params = new HashMap<>();
        return bbsPostsDao.getTopList(params);
    }

    @Override
    public BbsPostsEntity getPostByID(Map<String, Object> params) {
        return bbsPostsDao.getPostByID(params);
    }

    @Override
    public List<BbsPostsEntity> getPostByCollection(Integer userId) {
        return bbsPostsDao.getPostByCollection(userId);
    }
}
