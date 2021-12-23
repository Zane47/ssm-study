package com.imooc.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer categoryLevel;
    private Integer categoryOrder;
}
