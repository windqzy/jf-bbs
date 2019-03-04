package com.jfsoft.bbs.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.JWTUtils;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsGradeService;
import com.jfsoft.bbs.service.BbsSignService;
import com.jfsoft.bbs.service.BbsUserService;
import com.jfsoft.bbs.service.DingDingInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class LoginController {

    @Value("${app.DD_APPID}")
    String DD_APPID;

    @Value("${app.DD_APPSECRET}")
    String DD_APPSECRET;

    @Value("${app.DD_CORPID}")
    String DD_CORPID;

    @Value("${app.DD_CORPSECRET}")
    String DD_CORPSECRET;

    @Value("${app.DD_URL}")
    String DD_URL;

    @Value("${app.webUrl}")
    String webUrl;

    @Autowired
    private BbsUserService bbsUserService;

    @Autowired
    private BbsSignService bbsSignService;

    @Autowired
    private BbsGradeService bbsGradeService;

    @Autowired
    private DingDingInterfaceService dingDingInterfaceService;

    @GetMapping("/login/ding")
    public String getUserFromDingDing(String code) {

//            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
//            OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
//            req.setTmpAuthCode(code);
//            OapiSnsGetuserinfoBycodeResponse response = client.execute(req, DD_APPID, DD_APPSECRET);

        String name = null;
        String mobile = null;
        String position = null;
        String token = null;

        String accessToken = dingDingInterfaceService.getAccessToken().getStr("access_token");
        String companyToken = dingDingInterfaceService.getCompanyToken().getStr("access_token");
        String unionId = dingDingInterfaceService.getUnionId(accessToken, code).getStr("unionid");
        String userId = dingDingInterfaceService.getUserId(companyToken, unionId).getStr("userid");
        JSONObject user = dingDingInterfaceService.getUser(companyToken, userId);
        name = (String) user.get("name");
        mobile = (String) user.get("mobile");
        position = (String) user.get("position");

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
            BbsGradeEntity gradeEntity = new BbsGradeEntity();

            gradeEntity.setGrade(100);
            gradeEntity.setInitTime(new Date());
            gradeEntity.setUserId(id);
            bbsGradeService.insert(gradeEntity);
            token = JWTUtils.sign(String.valueOf(id), unionId);
            return "redirect:" + webUrl + "?token=" + token;
        } else {
            token = JWTUtils.sign(String.valueOf(bbsUser.getId()), unionId);
            return "redirect:" + webUrl + "?token=" + token;
        }

    }

    public static void main(String[] args) {
        System.out.println(JWTUtils.sign(String.valueOf(76), "gDJv4xQT2iivMfSaTobeNFAiEiE"));
        System.out.println(JWTUtils.sign(String.valueOf(77), "gDJv4xQT2iivMfSaTobeNFAiaaa"));
    }
}
