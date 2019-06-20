package com.jfsoft.bbs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfsoft.bbs.dao.BbsPostsDao;
import com.jfsoft.bbs.es.service.EsSearchService;
import com.jfsoft.bbs.form.ReplayVo;
import com.jfsoft.bbs.service.BbsReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JfBbsApplicationTests {

    @Autowired
    private EsSearchService esSearchService;

    @Autowired
    private BbsPostsDao bbsPostsDao;

    @Autowired
    private BbsReplyService bbsReplyService;

    @Test
    public void contextLoads() {
        List<ReplayVo> replyList = bbsReplyService.getReplayByPostsId(283);

        System.out.println(JSON.toJSONString(replyList,
                SerializerFeature.WriteMapNullValue));
    }

}
