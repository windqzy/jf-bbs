package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsGradeRuleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分规则表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@Mapper
public interface BbsGradeRuleDao extends BaseMapper<BbsGradeRuleEntity> {

    Integer getGradeByRule(Integer signCount);
	
}
