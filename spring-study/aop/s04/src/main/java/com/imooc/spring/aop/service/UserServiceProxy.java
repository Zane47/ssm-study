package com.imooc.spring.aop.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceProxy implements UserService {

    // 持有委托类的对象
    private final UserService userService;

    /**
     *
     */
    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser() {
        // 执行目标方法前, 打印目标方法的执行时间
        System.out.println("======= " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " =======");
        userService.createUser();
    }
}
