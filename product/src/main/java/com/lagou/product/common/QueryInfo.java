package com.lagou.product.common;

import lombok.Data;

@Data
public class QueryInfo {
    private String name;
    private Integer pagenum;
    private Double startprice;
    private Double endprice;
}
