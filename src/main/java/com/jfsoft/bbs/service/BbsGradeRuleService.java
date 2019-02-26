package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGradeRuleEntity;

import java.util.Map;

/**
 * 积分规则表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
public interface BbsGradeRuleService extends IService<BbsGradeRuleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer getGradeByRule(Integer signCount);
}