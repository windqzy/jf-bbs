package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsTagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsTagDao extends BaseMapper<BbsTagEntity> {

	List<BbsTagEntity> getTagByLabelId(Integer labelId);

}
