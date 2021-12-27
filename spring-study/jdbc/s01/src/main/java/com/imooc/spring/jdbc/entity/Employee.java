package com.imooc.spring.jdbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 属性名和数据库字段名, 驼峰形式对应
 */
@Getter
@Setter
@ToString
public class Employee {
    private Integer eno;
    private String eName;
    private Float salary;
    private String dName;
    private Date hiredate;
}
