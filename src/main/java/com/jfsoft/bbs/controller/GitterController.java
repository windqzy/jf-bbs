package com.jfsoft.bbs.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfsoft.bbs.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/4/8  10:46
 */
@RestController
@RequestMapping("/gitter")
public class GitterController {

    private static final String TRENDING_REPO_URL = "https://gitter-weapp.herokuapp.com/repositories?language=LANGUAGE&since=SINCE";
    private static final String TRENDING_USER_URL = "https://gitter-weapp.herokuapp.com/developers?language=LANGUAGE&since=SINCE";

    /**
     * @param type     类型   repo  user
     * @param language 语言   '' ...
     * @param since    时间
     * @return
     */
    @RequestMapping("/trending")
    public R getTrendingList(String type, String language, String since) {
        String res = null;
        JSONArray arr = null;
        if (type.equals("repo")) {
            res = HttpUtil.get(TRENDING_REPO_URL.replace("LANGUAGE", language).replace("SINCE", since));
            arr = JSON.parseArray(res);
        } else {
            res = HttpUtil.get(TRENDING_USER_URL.replace("LANGUAGE", language).replace("SINCE", since));
            arr = JSON.parseArray(res);
        }
        return R.ok().put("data", arr);
    }
}
