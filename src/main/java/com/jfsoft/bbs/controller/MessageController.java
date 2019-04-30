/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MessageController
 * Author:   Administrator
 * Date:     2019/4/25 16:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsMessageEntity;
import com.jfsoft.bbs.service.BbsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/msg")
public class MessageController extends AbstractController {

    @Autowired
    private BbsMessageService bbsMessageService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsMessageService.queryPage(params, null);
        return R.ok().put("data", page);
    }

    @RequestMapping("/save")
    public R save(@RequestBody BbsMessageEntity bbsMessageEntity){
//        bbsMessageService
        boolean insert = bbsMessageService.insert(bbsMessageEntity);
        return R.ok().put("data", insert);
    }

    @RequestMapping("/read/{id}")
    public R read(@PathVariable("id") Integer id){
        BbsMessageEntity bbsMessageEntity = new BbsMessageEntity();
        bbsMessageEntity.setId(id);
        bbsMessageEntity.setReadTime(new Date());
        bbsMessageEntity.setIsuse(false);
        boolean updateById = bbsMessageService.updateById(bbsMessageEntity);
        return R.ok().put("data", "已读");
    }


}