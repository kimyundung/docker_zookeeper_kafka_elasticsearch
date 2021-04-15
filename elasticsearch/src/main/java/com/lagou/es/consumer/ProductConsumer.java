package com.lagou.es.consumer;

import com.alibaba.fastjson.JSON;
import com.lagou.es.index.AddDocument;
import com.lagou.es.pojo.Products;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {
    @Autowired
    private AddDocument addDocument;

    @KafkaListener(topics ="product")
    public void productId(ConsumerRecord<String, String> record){
        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
        Products products = JSON.parseObject(record.value(), Products.class);
        addDocument.addDocument(products);
    }
}
