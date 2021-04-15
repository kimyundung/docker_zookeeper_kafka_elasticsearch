package com.lagou.es.index;

import com.lagou.es.pojo.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDocument {
    @Autowired
    private ProductRepository productRepository;

    /**
     * 索引库中添加索引
     * @param products
     */
    public void addDocument(Products products){
        productRepository.save(products);
        System.out.println(">>>>>>>>>>>> 添加索引成功");
    }
}
