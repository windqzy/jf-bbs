package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;

import java.util.List;
import java.util.Map;

public interface BbsGameGradeService extends IService<BbsGameGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    BbsGameGradeEntity getFirstGrade(Integer id);

    List<BbsGameEntity> getRankList(Integer gameId);

    List<BbsGameGradeEntity> getSumRankList();
}
