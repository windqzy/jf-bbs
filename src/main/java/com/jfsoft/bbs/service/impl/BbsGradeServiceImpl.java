package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsGradeDao;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.service.BbsGradeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsGradeService")
public class BbsGradeServiceImpl extends ServiceImpl<BbsGradeDao, BbsGradeEntity> implements BbsGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsGradeEntity> page = this.selectPage(
                new Query<BbsGradeEntity>(params).getPage(),
                new EntityWrapper<BbsGradeEntity>()
        );

        return new PageUtils(page);
    }

}
