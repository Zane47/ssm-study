package com.imooc.spring.aop.dao;

import org.springframework.stereotype.Repository;

/**
 * 员工表Dao
 */
@Repository
public class EmployeeDao {
    public void insert(){
        System.out.println("insert one record to Employee");
    }
}
