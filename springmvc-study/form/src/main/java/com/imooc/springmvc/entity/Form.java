package com.imooc.springmvc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Form {
    private String name;
    private String course;

    private List<Integer> purpose;

    // 这里需要实例化才可以保证赋值成功
    private Delivery delivery = new Delivery();

}
