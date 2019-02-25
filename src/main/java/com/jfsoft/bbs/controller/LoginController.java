package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.JWTUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.form.LoginForm;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private BbsUserService bbsUserService;

    @PostMapping
    public R login(@RequestBody LoginForm loginForm) {
        String unionId = loginForm.getUnionId();
        String name = loginForm.getName();
        String mobile = loginForm.getMobile();
        String position = loginForm.getPosition();
        String token = null;

        EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("union_id", unionId);

        BbsUserEntity bbsUser = bbsUserService.selectOne(wrapper);
        if (bbsUser == null) {
            //insert
            BbsUserEntity bbsUserEntity = new BbsUserEntity();
            bbsUserEntity.setInitTime(new Date());
            bbsUserEntity.setUnionId(unionId);
            bbsUserEntity.setName(name);
            bbsUserEntity.setMobile(mobile);
            bbsUserEntity.setPosition(position);
            bbsUserService.insert(bbsUserEntity);
            int id = bbsUserEntity.getId();
            token = JWTUtils.sign(String.valueOf(id), unionId);
            return R.ok().put("data", bbsUserEntity).put("token", token);
        } else {
            token = JWTUtils.sign(String.valueOf(bbsUser.getId()), unionId);
            return R.ok().put("data", bbsUser).put("token", token);
        }
    }
}
