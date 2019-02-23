package com.jfsoft.bbs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsPostsDao;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bbsPostsService")
public class BbsPostsServiceImpl extends ServiceImpl<BbsPostsDao, BbsPostsEntity> implements BbsPostsService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsPostsEntity> page = this.selectPage(
                new Query<BbsPostsEntity>(params).getPage(),
                new EntityWrapper<BbsPostsEntity>()
        );

        return new PageUtils(page);
    }

}
