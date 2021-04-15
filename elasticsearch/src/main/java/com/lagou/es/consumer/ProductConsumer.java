package com.lagou.es.consumer;

import com.lagou.es.index.AddDocument;
import com.lagou.es.mapper.ProductMapper;
import com.lagou.es.pojo.Products;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {
    @Autowired
    private AddDocument addDocument;
    @Autowired
    private ProductMapper productMapper;

    // 监听Kafka的product主题
    @KafkaListener(topics ="product")
    public void productId(ConsumerRecord<String, String> record){
        // 收到新消息（新插入商品的id）
        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
        int id = Integer.parseInt(record.value());
        // 从数据库中查询该id对象的商品对象
        Products products = productMapper.queryById(id);
        // 商品对象，添加到Elasticsearch的lagou索引库中。
        addDocument.addDocument(products);
    }
}
