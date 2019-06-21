package com.jfsoft.bbs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqLogDao;
import com.jfsoft.bbs.entity.BbsFaqLogEntity;
import com.jfsoft.bbs.service.BbsFaqLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("bbsFaqLogService")
public class BbsFaqLogServiceImpl extends ServiceImpl<BbsFaqLogDao, BbsFaqLogEntity> implements BbsFaqLogService {

    @Autowired
    private BbsFaqLogDao bbsFaqLogDao;

    @Override
    public Integer useCount(Integer faqId, Boolean isGood) {
        Map<String, Object> map = new HashMap<>();
        map.put("faqId", faqId);
        map.put("isGood", isGood);
        return bbsFaqLogDao.useCount(map);
    }
}
