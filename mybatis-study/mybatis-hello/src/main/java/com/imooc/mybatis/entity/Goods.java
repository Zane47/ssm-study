package com.imooc.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
/**
 * 与数据库字段对应, 类型保持一致
 */
public class Goods {
    private Integer goodsId;
    private String  title;
    private String  subTitle;
    private Float originalCost;
    private Float currentPrice;
    private Float  discount;
    private Integer isFreeDelivery;
    private Integer categoryId;

    private List<GoodsDetail> goodsDetailList;
}
