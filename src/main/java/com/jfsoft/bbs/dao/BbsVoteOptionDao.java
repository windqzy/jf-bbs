package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsVoteOptionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BbsVoteOptionDao extends BaseMapper<BbsVoteOptionEntity> {

    List<BbsVoteOptionEntity> getVoteOptionByPostId(Map<String, Object> params);
}
