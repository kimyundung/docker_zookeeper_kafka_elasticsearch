package com.lagou.product.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "lagou", type = "product", shards = 1, replicas = 1)
public class Products {

    @TableId(value = "id",type = IdType.AUTO)
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
