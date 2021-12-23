package com.imooc.spring.ioc;

import com.imooc.spring.ioc.dao.UserDao;
import com.imooc.spring.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("=======ioc容器已初始化========");

        /*UserDao bean1 = context.getBean("userdao", UserDao.class);
        UserDao bean2 = context.getBean("userdao", UserDao.class);
        UserDao bean3 = context.getBean("userdao", UserDao.class);*/

        UserService userService = context.getBean("userservice", UserService.class);
        UserService userService1 = context.getBean("userservice", UserService.class);
        UserService userService2 = context.getBean("userservice", UserService.class);
        UserService userService3 = context.getBean("userservice", UserService.class);
        UserService userService4 = context.getBean("userservice", UserService.class);


    }
}
