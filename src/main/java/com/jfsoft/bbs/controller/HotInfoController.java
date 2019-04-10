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

    private static final String HEALTH_CN_HOST = "https://www.cn-healthcare.com";
    private static final String HEALTH_CN_LIST = HEALTH_CN_HOST + "/api/article/articlelist?data=";
    private static final String HEALTH_CN_LIST72 = HEALTH_CN_HOST + "/api/article/nhourarticlelist?data={\"hot\":\"-72\"}";
    private static final String HEALTH_CN_LIST20 = HEALTH_CN_HOST + "/freezingapi/api/article/articletop20";
    private static final String HEALTH_CN_VO = HEALTH_CN_HOST + "/freezingapi/api/article/articleVo?data=";

    /**
     * @param type 类型   health-cn
     * @return
     */
    @RequestMapping("/list")
    public R getArticleList(String type, String start, String size, String arctype) {
        switch (type) {
            case "health":
                return getHealthCN(start, size, arctype);
            default:
                return null;
        }
    }

    @RequestMapping("/info")
    public R getArticleInfo(String type, String articleId) {

        switch (type) {
            case "health":
                return getHealthCNarctVo(articleId);
            default:
                return null;
        }

    }

    private static R getHealthCNarctVo(String articleId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articleId", articleId);
        String s = HttpUtil.get(HEALTH_CN_VO + jsonObject);
        JSONObject result = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) result.get("data");
        return R.ok().put("data", data);
    }

    @RequestMapping("/getHealthCN72")
    public R getHealthCN72() {
        String s = HttpUtil.get(HEALTH_CN_LIST72);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        return R.ok().put("data", array);
    }

    @RequestMapping("/getHealthCN20")
    public R getHealthCN20() {
        String s = HttpUtil.get(HEALTH_CN_LIST20);
        JSONArray array = (JSONArray) JSON.parse(s);
        return R.ok().put("data", array);
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
            articleForm.setCover(HEALTH_CN_HOST + article.getString("litpic"));
            articleForm.setPubdate(DateUtil.date(article.getLong("pubdate")));
            articleForm.setDescription(article.getString("description"));
            articleForm.setSource(article.getString("source"));
            articleList.add(articleForm);
        }

        return R.ok().put("data", articleList);
    }
}
