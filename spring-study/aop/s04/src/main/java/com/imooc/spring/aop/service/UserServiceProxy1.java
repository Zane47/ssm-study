package com.imooc.spring.aop.service;


public class UserServiceProxy1 implements UserService {

    // 持有委托类的对象
    private final UserService userService;

    /**
     *
     */
    public UserServiceProxy1(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser() {
        userService.createUser();
        System.out.println("======" + "后置拓展功能" + "======");
    }
}
