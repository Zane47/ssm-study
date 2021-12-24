package com.imooc.spring;

import com.imooc.spring.service.DepartmentService;
import com.imooc.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.getUserDao());

        /*String[] ids = context.getBeanDefinitionNames();
        for (String id : ids) {
            System.out.println(id + ": " + context.getBean(id));
        }*/

        DepartmentService departmentService = context.getBean("departmentService", DepartmentService.class);
        departmentService.joinDepartment();


    }
}
