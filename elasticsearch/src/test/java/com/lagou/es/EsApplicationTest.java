package com.lagou.es;

import com.lagou.es.pojo.Products;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void testMapping(){
        template.putMapping(Products.class);
    }
}
