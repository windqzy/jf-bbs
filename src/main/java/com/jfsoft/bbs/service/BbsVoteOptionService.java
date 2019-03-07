package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsVoteOptionEntity;

import java.util.List;
import java.util.Map;

public interface BbsVoteOptionService extends IService<BbsVoteOptionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BbsVoteOptionEntity> getVoteOptionByPostId(Map<String, Object> params);
}
