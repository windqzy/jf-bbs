package com.jfsoft.bbs.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.jfsoft.bbs.service.DingDingInterfaceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/3/1  17:04
 */
@Service("dingDingInterfaceService")
public class DingDingInterfaceServiceImpl implements DingDingInterfaceService {

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

    @Override
    public JSONObject getAccessToken() {
        JSONObject object;
        String jsonStr = HttpUtil.get(DD_URL + "/sns/gettoken?appid=" + DD_APPID + "&appsecret=" + DD_APPSECRET);
        try {
            object = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException("获取accessToken失败");
        }
        return object;
    }

    @Override
    public JSONObject getCompanyToken() {
        JSONObject object;
        String jsonStr = HttpUtil.get(DD_URL + "/gettoken?corpid=" + DD_CORPID + "&corpsecret=" + DD_CORPSECRET);
        try {
            object = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException("获取companyToken失败");
        }
        return object;
    }

    @Override
    public JSONObject getUnionId(String accessToken, String tmpAuthCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("tmp_auth_code", tmpAuthCode);
        JSONObject object;
        String jsonStr = HttpUtil.post(DD_URL + "/sns/get_persistent_code?access_token=" + accessToken
                , JSON.toJSONString(params));
        try {
            object = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException("获取unionId失败");
        }
        return object;
    }

    @Override
    public JSONObject getUserId(String companyToken, String unionId) {
        JSONObject object;
        String jsonStr = HttpUtil.get(DD_URL + "/user/getUseridByUnionid?access_token=" + companyToken +
                "&unionid=" + unionId);
        try {
            object = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException("获取userId失败");
        }
        return object;
    }

    @Override
    public JSONObject getUser(String companyToken, String userId) {
        JSONObject object;
        String jsonStr = HttpUtil.get(DD_URL + "/user/get?access_token=" + companyToken + "&userid=" + userId);
        try {
            object = JSONUtil.parseObj(jsonStr);
        } catch (Exception e) {
            throw new RuntimeException("获取user失败");
        }
        return object;
    }
}
