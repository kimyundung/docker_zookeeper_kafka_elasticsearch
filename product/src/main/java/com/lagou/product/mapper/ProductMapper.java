package com.lagou.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.product.common.Products;

public interface ProductMapper extends BaseMapper<Products> {

    /**
     * 添加
     * @param products
     * @return
     */
    public Integer createProduct(Products products);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Products queryById(Integer id);
}
