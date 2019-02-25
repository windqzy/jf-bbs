package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsReplyDao;
import com.jfsoft.bbs.dao.BbsUpDao;
import com.jfsoft.bbs.entity.BbsUpEntity;
import com.jfsoft.bbs.service.BbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("bbsUpService")
public class BbsUpServiceImpl extends ServiceImpl<BbsUpDao, BbsUpEntity> implements BbsUpService {

    @Autowired
    BbsUpDao bbsUpDao;

    @Autowired
    BbsReplyDao bbsReplyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsUpEntity> page = this.selectPage(
                new Query<BbsUpEntity>(params).getPage(),
                new EntityWrapper<BbsUpEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public BbsUpEntity getUpByRidUid(Integer userId, Integer replyId) {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("replyId",replyId);
        return bbsUpDao.getUpByRidUid(params);
    }

    @Override
    public Integer insertUp(Integer userId, Integer replyId) {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("replyId",replyId);
        params.put("type",1);
        bbsReplyDao.updateUpById(params);
        return bbsUpDao.insertUp(params);
    }

    @Override
    public Integer updateUp(Integer userId, Integer replyId, Boolean status) {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("replyId",replyId);
        if (status) {
            params.put("type",0);
        } else {
            params.put("type",1);
        }
        bbsReplyDao.updateUpById(params);
        return bbsUpDao.updateUp(params);
    }

}
