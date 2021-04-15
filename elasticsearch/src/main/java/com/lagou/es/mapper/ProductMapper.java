package com.lagou.es.mapper;

import com.lagou.es.pojo.Products;

public interface ProductMapper {
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Products queryById(Integer id);
}
