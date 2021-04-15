package com.lagou.product.controller;

import com.lagou.product.common.Products;
import com.lagou.product.common.QueryInfo;
import com.lagou.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //  主题名
        String topic = "product";
        //  通过kafkaTemplate,发送商品id到Kafka的product主题中
        kafkaTemplate.send(topic, products.getId().toString());

        return i==1? "success": "fail";
    }

    /**
     * 名称查询(索引) 方法
     * @param queryInfo
     * @return
     */
    @GetMapping("/query")
    public List<Products> queryByName(QueryInfo queryInfo){
        System.out.println(">>>>>>>>>>>>>>> "+ queryInfo);
        List<Products> products = productService.queryByName(queryInfo);
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
