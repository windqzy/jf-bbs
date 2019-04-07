package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsCollectionEntity;
import com.jfsoft.bbs.entity.BbsRewardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsRewardDao extends BaseMapper<BbsRewardEntity> {

    List<BbsRewardEntity> findRewardTopThree(String postsId);

}
