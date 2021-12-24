package com.imooc.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * CRUD
 * 组件类型注解默认beanId为类名首字母小写userDao
 *
 */
@Repository
public class UserDao implements IUserDao {
    public UserDao() {
        System.out.println("UserDao constructor " + this);
    }
}
