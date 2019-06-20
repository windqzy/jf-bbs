package com.jfsoft.bbs.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.dao.BbsFaqLogDao;
import com.jfsoft.bbs.entity.BbsFaqLogEntity;
import com.jfsoft.bbs.service.BbsFaqLogService;
import org.springframework.stereotype.Service;

@Service("bbsFaqLogService")
public class BbsFaqLogServiceImpl extends ServiceImpl<BbsFaqLogDao, BbsFaqLogEntity> implements BbsFaqLogService {
}
