package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsReplyDao;
import com.jfsoft.bbs.entity.BbsReplyEntity;
import com.jfsoft.bbs.service.BbsReplyService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("bbsReplyService")
public class BbsReplyServiceImpl extends ServiceImpl<BbsReplyDao, BbsReplyEntity> implements BbsReplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsReplyEntity> page = this.selectPage(
                new Query<BbsReplyEntity>(params).getPage(),
                new EntityWrapper<BbsReplyEntity>()
        );

        return new PageUtils(page);
    }

}
