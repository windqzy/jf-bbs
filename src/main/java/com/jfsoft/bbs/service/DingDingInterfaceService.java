package com.jfsoft.bbs.service;


import cn.hutool.json.JSONObject;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/3/1  16:58
 */
public interface DingDingInterfaceService {

    JSONObject getAccessToken();

    JSONObject getCompanyToken();

    JSONObject getUnionId(String accessToken, String tmpAuthCode);

    JSONObject getUserId(String companyToken, String unionId);

    JSONObject getUser(String companyToken, String userId);
}
