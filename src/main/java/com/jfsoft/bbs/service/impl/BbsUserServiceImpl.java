package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsUserDao;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsUserService")
public class BbsUserServiceImpl extends ServiceImpl<BbsUserDao, BbsUserEntity> implements BbsUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsUserEntity> page = this.selectPage(
                new Query<BbsUserEntity>(params).getPage(),
                new EntityWrapper<BbsUserEntity>()
        );

        return new PageUtils(page);
    }

}
