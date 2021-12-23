package com.imooc.spring.ioc.entity;


public class Child {
    private String name;
    private Apple apple;


    public Child() {
        System.out.println("创建child对象: " + this);
    }

    public Child(String name, Apple apple) {
        this.name = name;
        this.apple = apple;
    }

    public void eat() {
        System.out.println(this.name + " eat " + apple.getTitle() + " from " + apple.getOrigin());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        System.out.println("setApple: " + apple);
        this.apple = apple;
    }
}
