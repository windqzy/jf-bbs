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
import com.jfsoft.bbs.dao.BbsMessageDao;
import com.jfsoft.bbs.entity.BbsMessageEntity;
import com.jfsoft.bbs.service.BbsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/25
 * @since 1.0.0
 */
@Service("bbsMessageService")
public class BbsMessageServiceImpl extends ServiceImpl<BbsMessageDao, BbsMessageEntity> implements BbsMessageService {

    @Autowired
    private BbsMessageDao bbsMessageDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsMessageEntity> wrapper) {
        Page<BbsMessageEntity> page = this.selectPage(
                new Query<BbsMessageEntity>(params).getPage(),
                new EntityWrapper<BbsMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer addMsg(Integer msgType, String msgContent, Integer postId, Integer sendUserID, Integer userId, Boolean isuse) {
        Map<String, Object> params = new HashMap<>();
        params.put("msgType", msgType);
        params.put("msgContent", msgContent);
        if (postId != null) {
            params.put("postId", postId);
        } else {
            params.put("postId", null);
        }
        params.put("sendUserID", sendUserID);
        params.put("userId", userId);
        params.put("isuse", isuse);
//        bbsMessageDao.add
        return null;
    }
}