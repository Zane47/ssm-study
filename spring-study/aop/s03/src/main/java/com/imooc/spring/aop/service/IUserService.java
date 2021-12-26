package com.imooc.spring.aop.service;

public interface IUserService {
    public void createUser();

    public String generateRandomPassword(String type, Integer length);
}
