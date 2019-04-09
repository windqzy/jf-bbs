package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsGameGradeDao extends BaseMapper<BbsGameGradeEntity> {

    BbsGameGradeEntity getFirstGrade(Integer id);

    List<BbsGameEntity> getRankList(Integer gameId);

    List<BbsGameGradeEntity> getSumRankList();
}
