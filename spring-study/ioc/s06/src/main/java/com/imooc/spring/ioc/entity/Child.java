package com.imooc.spring.ioc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    private String name;
    private com.imooc.spring.ioc.entity.Apple apple;

    public void eat() {
        System.out.println(this.name + " eat " + apple.getTitle() + " from " + apple.getOrigin());
    }
}
