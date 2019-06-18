/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsMessageServiceImpl
 * Author:   Administrator
 * Date:     2019/4/25 16:49
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
import com.jfsoft.bbs.dao.BbsMessageUserDao;
import com.jfsoft.bbs.dao.BbsNewMessageDao;
import com.jfsoft.bbs.entity.BbsMessageUserEntity;
import com.jfsoft.bbs.entity.BbsNewMessageEntity;
import com.jfsoft.bbs.service.BbsMessageUserService;
import com.jfsoft.bbs.service.BbsNewMessageService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/25
 * @since 1.0.0
 */
@Service("bbsMessageUserService")
public class BbsMessageUserServiceImpl extends ServiceImpl<BbsMessageUserDao, BbsMessageUserEntity> implements BbsMessageUserService {


    @Override
    public PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsMessageUserEntity> wrapper) {
        Page<BbsMessageUserEntity> page = this.selectPage(
                new Query<BbsMessageUserEntity>(params).getPage(),
                new EntityWrapper<BbsMessageUserEntity>()
        );

        return new PageUtils(page);
    }
}