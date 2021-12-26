package com.imooc.spring.aop;

import com.imooc.spring.aop.service.UserService;
import com.imooc.spring.aop.service.UserServiceImpl;
import com.imooc.spring.aop.service.UserServiceProxy;
import com.imooc.spring.aop.service.UserServiceProxy1;

public class Application {
    public static void main(String[] args) {
        /*UserService userService = new UserServiceImpl();
        userService.createUser();*/

        UserService userService = new UserServiceProxy1(new UserServiceProxy(new UserServiceImpl()));
        userService.createUser();
    }
}
