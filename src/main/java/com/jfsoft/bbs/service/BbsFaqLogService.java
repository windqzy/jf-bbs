package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.entity.BbsFaqLogEntity;

public interface BbsFaqLogService extends IService<BbsFaqLogEntity> {
    Integer useCount(Integer faqId, Boolean isGood);
}
