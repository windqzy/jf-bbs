/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsVoteRecordServiceImpl
 * Author:   Administrator
 * Date:     2019/3/7 13:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsVoteRecordDao;
import com.jfsoft.bbs.entity.BbsVoteRecordEntity;
import com.jfsoft.bbs.service.BbsVoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/7
 * @since 1.0.0
 */
@Service("bbsVoteRecordService")
public class BbsVoteRecordServiceImpl extends ServiceImpl<BbsVoteRecordDao, BbsVoteRecordEntity> implements BbsVoteRecordService {

    @Autowired
    BbsVoteRecordDao bbsVoteRecordDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsVoteRecordEntity> page = this.selectPage(
                new Query<BbsVoteRecordEntity>(params).getPage(),
                new EntityWrapper<BbsVoteRecordEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public BbsVoteRecordEntity getRecord(Map<String, Object> params) {
        return bbsVoteRecordDao.getRecord(params);
    }
}