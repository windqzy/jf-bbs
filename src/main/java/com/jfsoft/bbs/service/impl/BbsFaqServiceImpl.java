package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqDao;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import com.jfsoft.bbs.service.BbsFaqService;
import org.springframework.stereotype.Service;

@Service("bbsFaqService")
public class BbsFaqServiceImpl extends ServiceImpl<BbsFaqDao, BbsFaqEntity> implements BbsFaqService {

}
