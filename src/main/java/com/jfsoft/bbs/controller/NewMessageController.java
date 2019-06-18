package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsMessageUserEntity;
import com.jfsoft.bbs.form.MessageVo;
import com.jfsoft.bbs.service.BbsMessageUserService;
import com.jfsoft.bbs.service.BbsNewMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName NewMessageController
 * @Description 消息管理
 * @Author Lxd
 * @Date 2019/6/18 13:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("/new/msg")
public class NewMessageController extends AbstractController {

    @Autowired
    private BbsNewMessageService bbsNewMessageService;

    @Autowired
    private BbsMessageUserService bbsMessageUserService;

    /**
     * 查询所有用户消息
     *
     * @Author Lxd
     * @Date 14:20 2019/6/18
     * @Param []
     * @Return
     **/
    @RequestMapping("/list")
    public R list() {
        List<MessageVo> messageVoList = bbsNewMessageService.querySelfMessage(getUserId());

        BbsMessageUserEntity bbsMessageUserEntity = new BbsMessageUserEntity();
        bbsMessageUserEntity.setIsRead("1");

        /** 将消息标记为已读 */
        EntityWrapper<BbsMessageUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("USER_ID", getUserId());
        bbsMessageUserService.update(bbsMessageUserEntity, wrapper);

        return R.ok().put("data", messageVoList);
    }

}
