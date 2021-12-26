package com.imooc.spring.aop.service;

public class UserServiceImpl implements UserService {
    @Override
    public void createUser() {
        System.out.println("create user");
    }
}
