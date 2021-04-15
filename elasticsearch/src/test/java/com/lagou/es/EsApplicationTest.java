package com.lagou.es;

import com.lagou.es.pojo.Products;
import com.lagou.product.service.ProductService;
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


    /**
     * 新建索引库lagou，类型名称为product，Mapping要求：
     * name：   "type": "text","analyzer": "ik_max_word"
     * goods_desc： "type": "text","analyzer": "ik_max_word"
     * 其它映射关系学员根据数据类型、需求场景自行定义。
     */
    @Test
    public void testMapping(){
        template.putMapping(Products.class);
    }
}
