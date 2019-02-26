package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 用户积分表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@Mapper
public interface BbsGradeDao extends BaseMapper<BbsGradeEntity> {

	Integer updateGradeByUserId(BbsGradeEntity bbsGradeEntity);

}
