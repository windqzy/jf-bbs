package com.jfsoft.bbs.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.form.ArticleForm;
import com.jfsoft.bbs.form.ArticleVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

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


    private static final String TX_HOST = "http://140.143.153.135:3001";
    private static final String TX_HOST2 = "http://140.143.153.135:3002";
    private static final String CODE_DAILY_DATE = TX_HOST + "/api/daily_list";
    private static final String CODE_DAILY_INFO = TX_HOST + "/api/daily_info/";
    private static final String ZHIHU_LIST = TX_HOST2 + "/zhihu_news";
    private static final String ZHIHU_VO = TX_HOST2 + "/zhihu_news_detail?id=";

    private static final String KR_LIST = "https://36kr.com/pp/api/feed-stream";

    private static final String INFO_Q_LIST = "https://www.infoq.cn/public/v1/article/getList";
    private static final String INFO_Q_VO = "https://www.infoq.cn/public/v1/article/getDetail";
    private static final String INFO_Q_INDEX = "https://www.infoq.cn/public/v1/article/getIndexList";

    private static final String PM_COFFEE_LIST = "https://coffee.pmcaff.com/list";
    private static final String PM_COFFEE_VO = "https://coffee.pmcaff.com/article/";

    /**
     * @param type 类型   health-cn
     * @return
     */
    @RequestMapping("/list")
    public R getArticleList(String type, String start, String size, String arctype) {
        switch (type) {
            case "health":
                return getHealthCN(start, size, arctype);
            case "zhihu":
                return getZhiHuList();
            case "kr":
                return getKrList(start, size, arctype);
            case "infoQ":
                return getInfoQList(start, size, arctype);
            case "pm":
                return getPMList(start, size, arctype);
            default:
                return null;
        }
    }

    @RequestMapping("/info")
    public R getArticleInfo(String type, String articleId) {

        switch (type) {
            case "health":
                return getHealthCNarctVo(articleId);
            case "zhihu":
                return getZhiHuVo(articleId);
            case "kr":
                return getKrVo(articleId);
            case "infoQ":
                return getInfoQVo(articleId);
            case "pm":
                return getPMVo(articleId);
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


    @RequestMapping("/getInfoQIndex")
    public R getInfoQIndex() {
        String s = HttpRequest.get(INFO_Q_INDEX)
                .header("Referer", "https://www.infoq.cn/")
                .execute()
                .body();
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
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

    @RequestMapping("/getCoderDailyDate")
    public JSONObject getCoderDayilyDate() {
        String s = HttpUtil.get(CODE_DAILY_DATE);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        return jsonObject;
    }

    @RequestMapping("/getCoderDailyInfo")
    public JSONObject getCoderDailyInfo(String date) {
        String s = HttpUtil.get(CODE_DAILY_INFO + date);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        return jsonObject;
    }


    private static R getHealthCN(String start, String size, String arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        Map<String, Object> map = new LinkedHashMap<>();
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
            articleForm.setId(article.getString("id"));
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

    private static R getHeadlines(String start, String size, Integer arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        String host = "http://m.toutiao.com";
        //0 热点新闻 1 社会新闻 2 娱乐新闻 3体育新闻 4美文 散文 5科技 6 财经 7 时尚
        String path;
        switch (arctype) {
            case 0:
                path = "/list/?tag=news_hot&ac=wap&count=20&format=json_raw&as=A1A59982B911729&cp=5929E12752796E1&min_behot_time=0";
                break;
            case 1:
                path = "/list/?tag=news_society&ac=wap&count=20&format=json_raw&as=A195B9F229018CD&cp=592991783C9D8E1&min_behot_time=0";
                break;
            case 2:
                path = "/list/?tag=news_entertainment&ac=wap&count=20&format=json_raw&as=A1C51992996195E&cp=5929D119B58EFE1&min_behot_time=0";
                break;
            case 3:
                path = "/list/?tag=news_sports&ac=wap&count=20&format=json_raw&as=A1054902B911A1E&cp=592991AA81AEAE1&min_behot_time=0";
                break;
            case 4:
                path = "/list/?tag=news_essay&ac=wap&count=20&format=json_raw&as=A195495279C19DE&cp=5929C1F91DFEEE1&min_behot_time=0";
                break;
            case 5:
                path = "/list/?tag=news_tech&ac=wap&count=20&format=json_raw&as=A1854972BABC6FF&cp=592A9CC64FCFAE1&max_behot_time=0";
                break;
            case 6:
                path = "/list/?tag=news_finance&ac=wap&count=20&format=json_raw&as=A145E9025A6C78B&cp=592ACC87687B1E1&max_behot_time=0";
                break;
            case 7:
                path = "/list/?tag=news_fashion&ac=wap&count=20&format=json_raw&as=A1353902AA9C7F9&cp=592ADCD7CF89AE1&max_behot_time=0";
                break;
            default:
                path = "/list/?tag=news_hot&ac=wap&count=20&format=json_raw&as=A1A59982B911729&cp=5929E12752796E1&min_behot_time=0";

        }
        String s = HttpUtil.get(host + path);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        return null;
    }

    private static R getZhiHuList() {
        List<ArticleForm> articleList = new ArrayList<>();
        String s = HttpUtil.get(ZHIHU_LIST);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray array = (JSONArray) data.get("stories");
        for (int i = 0; i < array.size(); i++) {
            ArticleForm articleForm = new ArticleForm();
            JSONObject article = array.getJSONObject(i);
            articleForm.setId(article.getString("id"));
            articleForm.setAuthor("知乎日报");
            articleForm.setTitle(article.getString("title"));
            articleForm.setDescription(null);
            articleForm.setCover((String) article.getJSONArray("images").get(0));
            articleForm.setPubdate(data.getDate("data"));
            articleForm.setSource("知乎日报");
            articleList.add(articleForm);
        }
        return R.ok().put("data", articleList);
    }

    private static R getKrList(String start, String size, String arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        String s;
        if (start.equals("1")) {
            s = HttpUtil.get(KR_LIST + "&feed_id=" + arctype + "&per_page=" + size);
        } else {
            s = HttpUtil.get(KR_LIST + "&feed_id=" + arctype + "&b_id=" + start + "&per_page=" + size);
        }
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray array = (JSONArray) data.get("items");
        for (int i = 0; i < array.size(); i++) {
            ArticleForm articleForm = new ArticleForm();
            JSONObject article = array.getJSONObject(i);
            articleForm.setId(article.getString("entity_id"));
            articleForm.setCode(article.getString("id"));
            JSONObject extra = (JSONObject) article.get("extra");
            JSONObject author = (JSONObject) extra.get("author_info");
            articleForm.setAuthor(author.getString("nickname"));
            articleForm.setTitle(article.getString("title"));
            articleForm.setDescription(article.getString("summary"));
            articleForm.setCover((String) article.getJSONArray("images").get(0));
            articleForm.setPubdate(article.getDate("published_at"));
            articleForm.setSource("36氪");
            articleForm.setPubdateStr(article.getString("updated_at"));
            articleList.add(articleForm);
        }
        return R.ok().put("data", articleList);
    }

    private static R getKrVo(String articleId) {
        String url = "https://36kr.com/p/";
        Document doc = null;
        try {
            doc = Jsoup.connect(url + articleId).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements body = doc.select(".article-mian-content");
        Elements title = doc.select(".article-title");
        ArticleVo articleVo = new ArticleVo();
        articleVo.setContent(body.toString());
        return R.ok().put("data", articleVo);
    }

    private static R getPMVo(String articleId) {
        Document doc = null;
        try {
            doc = Jsoup.connect(PM_COFFEE_VO + articleId).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements body = doc.select("#articleCont");
        ArticleVo articleVo = new ArticleVo();
        articleVo.setContent(body.toString());
        return R.ok().put("data", articleVo);
    }

    private static R getInfoQVo(String articleId) {
        Map<String, Object> body = new HashMap<>();
        body.put("uuid", articleId);
        String s = HttpRequest.post(INFO_Q_VO)
                .header("Referer", "https://www.infoq.cn/article/" + articleId)
                .body(JSON.toJSONString(body))
                .execute()
                .body();
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        ArticleVo articleVo = new ArticleVo();
        articleVo.setContent(data.getString("content"));
        articleVo.setTitle(data.getString("article_title"));
        return R.ok().put("data", articleVo);
    }

    private static R getZhiHuVo(String articleId) {
        String s = HttpUtil.get(ZHIHU_VO + articleId);
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        ArticleVo articleVo = new ArticleVo();
        articleVo.setTitle(data.getString("title"));
        articleVo.setContent(data.getString("body"));
        return R.ok().put("data", articleVo);
    }

    private static R getInfoQList(String start, String size, String arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        String referer = null;
        int type = 1;
        Map<String, Object> body = new HashMap<>();
        if (arctype.equals("8")) {
            referer = "https://www.infoq.cn/topic/architecture";
        } else if (arctype.equals("11")) {
            referer = "https://www.infoq.cn/topic/cloud-computing";
        } else if (arctype.equals("31")) {
            referer = "https://www.infoq.cn/topic/AI";
        } else if (arctype.equals("38")) {
            referer = "https://www.infoq.cn/topic/operation";
        } else if (arctype.equals("33")) {
            referer = "https://www.infoq.cn/topic/Front-end";
        }

        body.put("type", type);
        body.put("size", Integer.parseInt(size));
        body.put("id", Integer.parseInt(arctype));
        if (!start.equals("1")) {
            body.put("score", Long.parseLong(start));
        }

        String s = HttpRequest.post(INFO_Q_LIST)
                .header("Referer", referer)
                .body(JSON.toJSONString(body))
                .execute()
                .body();
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < array.size(); i++) {
            ArticleForm articleForm = new ArticleForm();
            JSONObject article = array.getJSONObject(i);
            articleForm.setId(article.getString("uuid"));
            articleForm.setCode(article.getString("score"));
            articleForm.setAuthor("InfoQ");
            articleForm.setTitle(article.getString("article_title"));
            articleForm.setDescription(article.getString("article_summary"));
            articleForm.setCover(article.getString("article_cover"));
            articleForm.setPubdate(DateUtil.date(article.getLong("publish_time")));
            articleForm.setSource("InfoQ");
            articleList.add(articleForm);
        }
        return R.ok().put("data", articleList);
    }

    private static R getPMList(String start, String size, String arctype) {
        List<ArticleForm> articleList = new ArrayList<>();
        Map<String, Object> form = new HashMap<>();
        form.put("page", Integer.parseInt(start));
        form.put("feed_sum", Integer.parseInt(size));
        form.put("type", Integer.parseInt(arctype));
        String s = HttpRequest.post(PM_COFFEE_LIST)
                .header("referer", "https://coffee.pmcaff.com/?type=" + arctype)
                .form(form)
                .execute()
                .body();
        JSONObject jsonObject = (JSONObject) JSON.parse(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < array.size(); i++) {
            ArticleForm articleForm = new ArticleForm();
            JSONObject article = array.getJSONObject(i);
            articleForm.setId(article.getString("id"));
            articleForm.setCode(article.getString("sourceId"));
            articleForm.setAuthor(article.getString("author"));
            articleForm.setTitle(article.getString("title"));
            articleForm.setDescription(article.getString("description"));
            articleForm.setCover(article.getString("picUrl"));
            articleForm.setPubdate(article.getDate("updated_at"));
            articleForm.setSource("PMCAFF");
            articleList.add(articleForm);
        }
        return R.ok().put("data", articleList);
    }

    public static void main(String[] args) {
//        String url = "http://m.ui.cn/study";
//        Document doc = null;
//        try {
//            doc = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Elements content = doc.select(".study-list");
//        Elements li = content.select(".cl");
//        for (Element element : li) {
//
//        }
        System.out.println(DateUtil.format(new Date(), "yyyyMMdd"));
    }
}
