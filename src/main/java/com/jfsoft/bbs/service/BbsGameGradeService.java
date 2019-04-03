package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;

import java.util.Map;

public interface BbsGameGradeService extends IService<BbsGameGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
