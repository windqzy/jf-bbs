package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;

import java.util.Map;

public interface BbsVoteRecordService extends IService<BbsVoteRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
