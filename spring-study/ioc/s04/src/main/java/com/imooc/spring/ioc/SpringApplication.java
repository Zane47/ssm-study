package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Company company = context.getBean("company", Company.class);
        System.out.println(company);

        String phone = company.getInfo().getProperty("phone");
        System.out.println(phone);
    }
}
