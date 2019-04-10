package com.jfsoft.bbs.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.form.ArticleForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/4/8  10:46
 */
@RestController
@RequestMapping("/hot")
public class HotInfoController {

    private static final String HEALTH_CN_LIST = "https://www.cn-healthcare.com/api/article/articlelist?data=";

    /**
     * @param type 类型   health-cn
     * @return
     */
    @RequestMapping("/list")
    public R getTrendingList(String type, String start, String size, String arctype) {
        switch (type) {
            case "health":
                return getHealthCN(start, size, arctype);
            default:
                return null;
        }
    }

    private static R getHealthCN(String start, String size, String arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        Map<String , Object> map = new LinkedHashMap<>();
        map.put("start", start);
        map.put("size", size);
        map.put("arctype", arctype);
        String s = HttpUtil.get(HEALTH_CN_LIST + JSON.toJSONString(map));
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        // 清洗数据
        for (int i = 0; i < array.size(); i++) {
            ArticleForm articleForm = new ArticleForm();
            JSONObject article = array.getJSONObject(i);
            articleForm.setAuthor(article.getString("author"));
            articleForm.setId(article.getInteger("id"));
            articleForm.setTitle(article.getString("title"));
            articleForm.setContent(article.getString("content"));
            articleForm.setCover(article.getString("litpic"));
            articleForm.setPubdate(DateUtil.date(article.getLong("pubdate")));
            articleForm.setDescription(article.getString("description"));
            articleForm.setSource(article.getString("source"));
            articleList.add(articleForm);
        }

        return R.ok().put("data", articleList);
    }
}
