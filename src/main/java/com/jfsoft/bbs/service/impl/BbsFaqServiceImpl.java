package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqDao;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import com.jfsoft.bbs.service.BbsFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bbsFaqService")
public class BbsFaqServiceImpl extends ServiceImpl<BbsFaqDao, BbsFaqEntity> implements BbsFaqService {

    @Autowired
    private BbsFaqDao bbsFaqDao;

    @Override
    public List<BbsFaqEntity> getFaqList(Integer typeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("typeId", typeId);
        return bbsFaqDao.getFaqList(params);
    }
}
