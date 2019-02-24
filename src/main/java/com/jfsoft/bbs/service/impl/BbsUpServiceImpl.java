package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsUpDao;
import com.jfsoft.bbs.entity.BbsUpEntity;
import com.jfsoft.bbs.service.BbsUpService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsUpService")
public class BbsUpServiceImpl extends ServiceImpl<BbsUpDao, BbsUpEntity> implements BbsUpService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsUpEntity> page = this.selectPage(
                new Query<BbsUpEntity>(params).getPage(),
                new EntityWrapper<BbsUpEntity>()
        );

        return new PageUtils(page);
    }

}
