package com.jfsoft.bbs;

import com.jfsoft.bbs.dao.BbsPostsDao;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.es.document.ProductDocument;
import com.jfsoft.bbs.es.document.ProductDocumentBuilder;
import com.jfsoft.bbs.es.service.EsSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JfBbsApplicationTests {

    @Autowired
    private EsSearchService esSearchService;
    @Autowired
    private BbsPostsDao bbsPostsDao;
    @Test
    public void contextLoads() {
        List<BbsPostsEntity> list = bbsPostsDao.findAll();
        for (BbsPostsEntity bbsPosts:list){
        ProductDocument productDocument = ProductDocumentBuilder.create()
                .addId(bbsPosts.getId().toString())
                .addProductName(bbsPosts.getTitle())
                .addProductDesc(bbsPosts.getContent())
                .addCreateTime(bbsPosts.getInitTime())
                .builder();
        esSearchService.save(productDocument);
        }
    }

}
