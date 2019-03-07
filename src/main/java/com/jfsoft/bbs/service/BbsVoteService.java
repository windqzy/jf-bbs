package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsVoteEntity;

import java.util.Map;

public interface BbsVoteService extends IService<BbsVoteEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
