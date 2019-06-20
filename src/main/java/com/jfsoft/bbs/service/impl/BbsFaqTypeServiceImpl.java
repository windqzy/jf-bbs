package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqTypeDao;
import com.jfsoft.bbs.entity.BbsFaqTypeEntity;
import com.jfsoft.bbs.service.BbsFaqTypeService;
import org.springframework.stereotype.Service;

@Service("bbsFaqTypeService")
public class BbsFaqTypeServiceImpl extends ServiceImpl<BbsFaqTypeDao, BbsFaqTypeEntity> implements BbsFaqTypeService {
}
