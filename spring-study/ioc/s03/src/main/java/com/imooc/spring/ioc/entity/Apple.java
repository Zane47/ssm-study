package com.imooc.spring.ioc.entity;


public class Apple {
    private String title;
    private String color;
    // 产地
    private String origin;

    private Double price;

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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        System.out.println("Apple setTitle: " + title);
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
