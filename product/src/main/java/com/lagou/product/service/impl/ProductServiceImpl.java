package com.lagou.product.service.impl;

import com.lagou.product.common.Products;
import com.lagou.product.common.QueryInfo;
import com.lagou.product.mapper.ProductMapper;
import com.lagou.product.resultmapper.ESSearchResultMapper;
import com.lagou.product.service.ProductService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ElasticsearchTemplate esTemplate;

    /**
     * 添加
     * @param products
     * @return
     */
    @Override
    public Integer createProduct(Products products) {
        return productMapper.createProduct(products);
    }

    /**
     * 名字查询(索引) 方法1
     * - 查询条件：name
     * - 分页查询：每页5条
     * - 排序规则：price，升序
     * 结果过滤：商品价格范围过滤
     * - 实现高亮效果
     * @param name
     * @param pagenow
     * @return
     */
    @Override
    public List<Products> queryByName(String name, Integer pagenow) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[0], new String[0]));
        // 查询条件:name
        queryBuilder.withQuery(QueryBuilders.matchQuery("name",name));
        // 分页查询:每页5条
        // 排序规则：price，升序
        queryBuilder.withPageable(PageRequest.of(pagenow-1,5, Sort.by(Sort.Direction.ASC, "price")));
        // 高亮
        queryBuilder.withHighlightBuilder(new HighlightBuilder().field("name"));
        HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<font style='color:red'>").postTags("</font>");
        queryBuilder.withHighlightFields(field); // 名字高亮
        NativeSearchQuery build = queryBuilder.build();
        // 查询+高亮
        AggregatedPage<Products> results = esTemplate.queryForPage(queryBuilder.build(), Products.class, new ESSearchResultMapper());
        List<Products> productList = results.getContent();
        System.out.println(">>>>>>>>>>>>>>>> " + productList);
        return productList;
    }

    /**
     *
     * 名字查询(索引) 方法2
     * - 查询条件：name
     * - 分页查询：每页5条
     * - 排序规则：price，升序
     * 结果过滤：商品价格范围过滤
     * - 实现高亮效果
     * @param queryInfo
     * @return
     */
    @Override
    public List<Products> queryByName2(QueryInfo queryInfo) {
        if(queryInfo.getName()!=null && queryInfo.getPagenum()!=null && queryInfo.getStartprice()!=null && queryInfo.getEndprice()!=null){

            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
            queryBuilder.withSourceFilter(new FetchSourceFilter(new String[0], new String[0]));
            // 查询条件:name
            BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
            boolQueryBuilder.must(QueryBuilders.matchQuery("name",queryInfo.getName()));
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(queryInfo.getStartprice()));
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(queryInfo.getEndprice()));
            queryBuilder.withQuery(boolQueryBuilder);
            // 分页查询:每页5条
            // 排序规则：price，升序
            queryBuilder.withPageable(PageRequest.of(queryInfo.getPagenum()-1,5, Sort.by(Sort.Direction.ASC, "price")));
            // 高亮
            queryBuilder.withHighlightBuilder(new HighlightBuilder().field("name"));
            HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<font style='color:red'>").postTags("</font>");
            queryBuilder.withHighlightFields(field); // 名字高亮
            NativeSearchQuery build = queryBuilder.build();
            // 查询+高亮
            AggregatedPage<Products> results = esTemplate.queryForPage(queryBuilder.build(), Products.class, new ESSearchResultMapper());
            List<Products> productList = results.getContent();
            System.out.println(">>>>>>>>>>>>>>>> " + productList);
            return productList;



        }
        return null;
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Products queryById(Integer id) {
        return productMapper.selectById(id);
    }
}
