package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqDao;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import com.jfsoft.bbs.service.BbsFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bbsFaqService")
public class BbsFaqServiceImpl extends ServiceImpl<BbsFaqDao, BbsFaqEntity> implements BbsFaqService {

    @Autowired
    private BbsFaqDao bbsFaqDao;

    @Override
    public List<BbsFaqEntity> getFaqList(Integer typeId) {
        return bbsFaqDao.getFaqList(typeId);
    }
}
