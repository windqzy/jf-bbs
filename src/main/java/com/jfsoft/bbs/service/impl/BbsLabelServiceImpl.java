package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsLabelDao;
import com.jfsoft.bbs.entity.BbsLabelEntity;
import com.jfsoft.bbs.service.BbsLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("bbsLabelService")
public class BbsLabelServiceImpl extends ServiceImpl<BbsLabelDao, BbsLabelEntity> implements BbsLabelService {

    @Autowired
    private BbsLabelDao bbsLabelDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsLabelEntity> page = this.selectPage(
                new Query<BbsLabelEntity>(params).getPage(),
                new EntityWrapper<BbsLabelEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BbsLabelEntity> getLabelByUserId(Map<String, Object> params) {
        return bbsLabelDao.getLabelByUserId(params);
    }

}
