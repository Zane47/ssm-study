package com.imooc.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDetail {
    private Integer gdId;
    private Integer goodsId;
    private String gdPicUrl;
    private Integer gdOrder;

    private Goods goods;
}
