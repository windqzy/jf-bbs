/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsCollectionServiceImpl
 * Author:   Administrator
 * Date:     2019/2/28 16:22
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
import com.jfsoft.bbs.dao.BbsCollectionDao;
import com.jfsoft.bbs.dao.BbsRewardDao;
import com.jfsoft.bbs.entity.BbsCollectionEntity;
import com.jfsoft.bbs.entity.BbsRewardEntity;
import com.jfsoft.bbs.service.BbsCollectionService;
import com.jfsoft.bbs.service.BbsRewardService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/28
 * @since 1.0.0
 */
@Service("bbsRewardService")
public class BbsRewardServiceImpl extends ServiceImpl<BbsRewardDao, BbsRewardEntity> implements BbsRewardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsRewardEntity> page = this.selectPage(
                new Query<BbsRewardEntity>(params).getPage(),
                new EntityWrapper<BbsRewardEntity>()
        );
        return new PageUtils(page);
    }
}