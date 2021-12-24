package com.imooc.spring.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Primary
public class UserOracleDao implements IUserDao {
    public UserOracleDao() {
        System.out.println("UserOracleDao constructor " + this);
    }

}
