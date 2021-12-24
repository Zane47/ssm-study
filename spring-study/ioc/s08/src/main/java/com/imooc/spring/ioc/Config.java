package com.imooc.spring.ioc;

import com.imooc.spring.ioc.controller.UserController;
import com.imooc.spring.ioc.dao.EmployeeDao;
import com.imooc.spring.ioc.dao.UserDao;
import com.imooc.spring.ioc.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.validation.annotation.Validated;

/**
 * java config, config类替代xml文件
 */
@Configuration// 当前类是一个配置类, 用于替代applicationContext.xml
@ComponentScan(basePackages = "com.imooc")
public class Config {

    // Java Config利用方法创建对象，将方法返回对象放入容器，beanId=方法名
    // <bean id="XXX" class="XXX"
    @Bean
    public UserDao userDao() {
        // 使用new关键字完成创建
        // 不要把这里看成是工程的一部分, 把他当成一个配置文件, 在userDao方法内部用来构建对象
        UserDao userDao = new UserDao();
        System.out.println("UserDao create: " + userDao);
        return userDao;
    }

    @Bean
    @Primary
    public UserDao userDao1() {
        // 使用new关键字完成创建
        // 不要把这里看成是工程的一部分, 把他当成一个配置文件, 在userDao方法内部用来构建对象
        UserDao userDao = new UserDao();
        System.out.println("UserDao create: " + userDao);
        return userDao;
    }


    @Bean
    public UserService userService(UserDao userDao, EmployeeDao employeeDao) {
        UserService userService = new UserService();
        System.out.println("userService create: " + userService);

        userService.setUserDao(userDao);
        System.out.println("userService.setUserDao: " + userDao);

        userService.setEmployeeDao(employeeDao);
        System.out.println("userService.employeeDao: " + employeeDao);

        return userService;
    }


    @Bean
    @Scope("prototype")
    public UserController userController(UserService userService) {
        UserController userController = new UserController();
        System.out.println("userController create: " + userController);
        userController.setUserService(userService);
        System.out.println("userController.setUserService: " + userService);
        return userController;
    }

}
