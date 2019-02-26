package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsGradeRuleDao;
import com.jfsoft.bbs.entity.BbsGradeRuleEntity;
import com.jfsoft.bbs.service.BbsGradeRuleService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsGradeRuleService")
public class BbsGradeRuleServiceImpl extends ServiceImpl<BbsGradeRuleDao, BbsGradeRuleEntity> implements BbsGradeRuleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsGradeRuleEntity> page = this.selectPage(
                new Query<BbsGradeRuleEntity>(params).getPage(),
                new EntityWrapper<BbsGradeRuleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer getGradeByRule(Integer signCount) {
        return null;
    }

}
