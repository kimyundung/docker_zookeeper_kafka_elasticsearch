package com.lagou.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * - 新建索引库lagou，类型名称为product，Mapping要求：
 * - name：   "type": "text","analyzer": "ik_max_word"
 * - goods_desc： "type": "text","analyzer": "ik_max_word"
 * - 其它映射关系学员根据数据类型、需求场景自行定义。
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "lagou", type = "product", shards = 1, replicas = 1) //索引库名称lagou，类型名称product
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
