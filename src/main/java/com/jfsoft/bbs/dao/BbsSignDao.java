package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsSignEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 签到表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@Mapper
public interface BbsSignDao extends BaseMapper<BbsSignEntity> {
	
}
