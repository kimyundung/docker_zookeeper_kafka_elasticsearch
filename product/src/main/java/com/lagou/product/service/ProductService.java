package com.lagou.product.service;

import com.lagou.product.common.Products;
import com.lagou.product.common.QueryInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductService {

    /**
     * 添加
     * @param products
     * @return
     */
    public Integer createProduct(Products products);

    /**
     * 名称查询(索引)
     * @param queryInfo
     * @return
     */
    public List<Products> queryByName(QueryInfo queryInfo);

    /**
     * 测试用的
     * 根据ID查询
     * @param id
     * @return
     */
    public Products queryById(Integer id);
}
