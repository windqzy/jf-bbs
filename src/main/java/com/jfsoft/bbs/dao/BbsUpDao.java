package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsUpEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 点赞记录表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-24 21:38:33
 */
@Mapper
public interface BbsUpDao extends BaseMapper<BbsUpEntity> {
	
}
