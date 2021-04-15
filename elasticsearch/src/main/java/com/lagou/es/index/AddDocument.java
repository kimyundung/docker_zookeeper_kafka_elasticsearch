package com.lagou.es.index;

import com.lagou.es.pojo.Products;
import com.lagou.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AddDocument {
    @Autowired
    private ProductRepository productRepository;
    @Reference
    private ProductService productService;

    /**
     * 索引库中添加索引
     * @param products
     */
    public void addDocument(Products products){
        productRepository.save(products);
        System.out.println("------------------ " +productService.queryById(products.getId()));
        System.out.println(">>>>>>>>>>>> 添加索引成功");
    }
}
