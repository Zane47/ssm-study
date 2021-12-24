package com.imooc.spring.ioc.dao;

import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDao {
    public EmployeeDao() {
        System.out.println("EmployeeDao create " + this);
    }
}
