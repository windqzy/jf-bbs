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
import com.jfsoft.bbs.dao.BbsNewMessageDao;
import com.jfsoft.bbs.entity.BbsNewMessageEntity;
import com.jfsoft.bbs.form.MessageVo;
import com.jfsoft.bbs.service.BbsNewMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/25
 * @since 1.0.0
 */
@Service("bbsNewMessageService")
public class BbsNewMessageServiceImpl extends ServiceImpl<BbsNewMessageDao, BbsNewMessageEntity> implements BbsNewMessageService {

    @Autowired
    private BbsNewMessageDao bbsNewMessageDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsNewMessageEntity> wrapper) {
        Page<BbsNewMessageEntity> page = this.selectPage(
                new Query<BbsNewMessageEntity>(params).getPage(),
                new EntityWrapper<BbsNewMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MessageVo> querySelfMessage(Integer userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        List<MessageVo> messageVoList = bbsNewMessageDao.querySelfMessage(params);
        return messageVoList;
    }
}