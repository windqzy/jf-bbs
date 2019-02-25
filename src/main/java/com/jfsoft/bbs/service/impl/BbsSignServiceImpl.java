package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsSignDao;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsSignService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsSignService")
public class BbsSignServiceImpl extends ServiceImpl<BbsSignDao, BbsSignEntity> implements BbsSignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsSignEntity> page = this.selectPage(
                new Query<BbsSignEntity>(params).getPage(),
                new EntityWrapper<BbsSignEntity>()
        );

        return new PageUtils(page);
    }

}
