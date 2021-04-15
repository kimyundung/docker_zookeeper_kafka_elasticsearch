package com.lagou.es.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "lagou", type = "product", shards = 1, replicas = 1)
public class Products {

    @Id
    private Integer id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword)
    private String flag;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String goods_desc;

    @Field(type = FieldType.Keyword)
    private String images;

    @Field(type = FieldType.Integer)
    private Integer goods_stock;

    @Field(type = FieldType.Keyword)
    private String goods_type;
}
