package com.imooc.mybatis.dto;

import com.imooc.mybatis.entity.Category;
import com.imooc.mybatis.entity.Goods;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDTO {
    private Goods goods = new Goods();
    private Category category = new Category();
    // private String categoryName;
    private String test;
}
