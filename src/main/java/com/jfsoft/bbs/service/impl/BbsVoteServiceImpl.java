/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsVoteServiceImpl
 * Author:   Administrator
 * Date:     2019/3/7 11:57
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
import com.jfsoft.bbs.dao.BbsVoteDao;
import com.jfsoft.bbs.entity.BbsVoteEntity;
import com.jfsoft.bbs.service.BbsVoteService;
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
@Service("bbsVoteService")
public class BbsVoteServiceImpl extends ServiceImpl<BbsVoteDao, BbsVoteEntity> implements BbsVoteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsVoteEntity> page = this.selectPage(
                new Query<BbsVoteEntity>(params).getPage(),
                new EntityWrapper<BbsVoteEntity>()
        );
        return new PageUtils(page);
    }
}