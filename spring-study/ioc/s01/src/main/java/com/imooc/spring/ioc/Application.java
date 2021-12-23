package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Apple;
import com.imooc.spring.ioc.entity.Child;

public class Application {
    public static void main(String[] args) {
        Apple apple1 = new Apple("红富士", "red", "Eur");
        Apple apple2 = new Apple("green apple", "green", "origin2");
        Apple apple3 = new Apple("金帅", "yellow", "China");

        // 孩子和苹果之间的对象关联
        Child child1 = new Child("child1", apple1);
        Child child2 = new Child("child2", apple2);
        Child child3 = new Child("child3", apple3);
        child1.eat();
        child2.eat();
        child3.eat();
    }
}
