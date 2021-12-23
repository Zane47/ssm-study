package com.imooc.spring.ioc.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apple {
    private String title;
    private String color;
    // 产地
    private String origin;

    private double price;

    public Apple() {
        System.out.println("apple 对象已创建" + this);
    }

    public Apple(String title, String color, String origin) {
        this.title = title;
        this.color = color;
        this.origin = origin;
        System.out.println("带参数的构造函数创建对象: " + this);
        System.out.println(this.title + " " + this.color + " " + this.origin);
    }

    public Apple(String title, String color, String origin, double price) {
        this.title = title;
        this.color = color;
        this.origin = origin;
        this.price = price;
        System.out.println("带参数的构造函数创建对象: " + this);
        System.out.println(this.title + " " + this.color + " " + this.origin + " " + this.price);
    }
}
