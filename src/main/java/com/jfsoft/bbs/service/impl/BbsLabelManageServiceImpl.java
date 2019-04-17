/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsLabelManageServiceImpl
 * Author:   Administrator
 * Date:     2019/4/16 15:29
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
import com.jfsoft.bbs.dao.BbsLabelManageDao;
import com.jfsoft.bbs.entity.BbsLabelManageEntity;
import com.jfsoft.bbs.service.BbsLabelManageService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/16
 * @since 1.0.0
 */
@Service("bbsLabelManageService")
public class BbsLabelManageServiceImpl extends ServiceImpl<BbsLabelManageDao, BbsLabelManageEntity> implements BbsLabelManageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsLabelManageEntity> page = this.selectPage(
                new Query<BbsLabelManageEntity>(params).getPage(),
                new EntityWrapper<BbsLabelManageEntity>()
        );

        return new PageUtils(page);
    }
}