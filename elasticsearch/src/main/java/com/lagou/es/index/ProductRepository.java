package com.lagou.es.index;

import com.lagou.es.pojo.Products;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Products,Integer> {
}
