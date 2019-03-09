package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BbsVoteRecordDao extends BaseMapper<BbsVoteRecordEntity> {

	BbsVoteRecordEntity getRecord(Map<String, Object> params);
}
