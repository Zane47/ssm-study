package com.imooc.spring.ioc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private String title;
    private String color;
    // 产地
    private String origin;

}
