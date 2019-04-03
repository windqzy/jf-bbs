/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsGameServiceImpl
 * Author:   Administrator
 * Date:     2019/4/3 15:14
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
import com.jfsoft.bbs.dao.BbsGameDao;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.service.BbsGameService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/3
 * @since 1.0.0
 */
@Service("bbsGameService")
public class BbsGameServiceImpl extends ServiceImpl<BbsGameDao, BbsGameEntity> implements BbsGameService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsGameEntity> page = this.selectPage(
                new Query<BbsGameEntity>(params).getPage(),
                new EntityWrapper<BbsGameEntity>()
        );

        return new PageUtils(page);
    }
}