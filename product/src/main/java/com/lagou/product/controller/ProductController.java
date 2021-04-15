package com.lagou.product.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lagou.product.common.Products;
import com.lagou.product.common.QueryInfo;
import com.lagou.product.service.ProductService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 添加product
     * @param products
     * @return
     */
    @PostMapping("/createProduct")
    public String createProduct (@RequestBody Products products){
        System.out.println(">>>>>>>>>>>>>>> "+ products);
        //1 持久化
        Integer i = productService.createProduct(products);
        System.out.println("<<<<<<<<<<<<<< " + products.getId());
        //2 生产消息(商品id)
        String topic = "product";
        String productsString = JSON.toJSONString(products);
        kafkaTemplate.send(topic, productsString);

        return i==1? "success": "fail";
    }

    /**
     * 名称查询(索引)
     * @param name
     * @return
     */
    @GetMapping("/query")
    public List<Products> queryByName(String name, Integer pagenow){
        System.out.println(">>>>>>>>>>>>>>> "+ name + " " + pagenow);
        List<Products> products = productService.queryByName(name, pagenow);
        return products;
    }

    /**
     * 名称查询(索引) 方法2
     * @param queryInfo
     * @return
     */
    @GetMapping("/query2")
    public List<Products> queryByName2(QueryInfo queryInfo){
        System.out.println(">>>>>>>>>>>>>>> "+ queryInfo);
        List<Products> products = productService.queryByName2(queryInfo);
        return products;
    }

    /**
     * 测试用的
     * 根据ID查询商品
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public Products queryById(@PathVariable Integer id){
        System.out.println(">>>>>>>>>>>>>>> "+ id);
        return productService.queryById(id);
    }
}
