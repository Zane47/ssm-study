package com.imooc.springmvc.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Form {
    private String name;
    private String course;

    private List<Integer> purpose;

}
