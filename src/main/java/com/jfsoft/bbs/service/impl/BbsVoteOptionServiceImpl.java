/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsVoteOptionServiceImpl
 * Author:   Administrator
 * Date:     2019/3/7 13:27
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
import com.jfsoft.bbs.dao.BbsVoteOptionDao;
import com.jfsoft.bbs.entity.BbsVoteOptionEntity;
import com.jfsoft.bbs.service.BbsVoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/7
 * @since 1.0.0
 */
@Service("bbsVoteOptionService")
public class BbsVoteOptionServiceImpl extends ServiceImpl<BbsVoteOptionDao, BbsVoteOptionEntity> implements BbsVoteOptionService {

    @Autowired
    BbsVoteOptionDao bbsVoteOptionDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsVoteOptionEntity> page = this.selectPage(
                new Query<BbsVoteOptionEntity>(params).getPage(),
                new EntityWrapper<BbsVoteOptionEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<BbsVoteOptionEntity> getVoteOptionByPostId(Map<String, Object> params) {
        return bbsVoteOptionDao.getVoteOptionByPostId(params);
    }
}