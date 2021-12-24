package com.imooc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        String[] ids = context.getBeanDefinitionNames();
        for (String id : ids) {
            System.out.println(id + ": " + context.getBean(id));
        }


    }
}
