package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Apple;
import com.imooc.spring.ioc.entity.Child;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        //创建SpringIoC容器，并根据配置文件在容器中实例化对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Apple sweetApple = context.getBean("sweetApple", Apple.class);
        System.out.println(sweetApple.getTitle());

        Child child1 = context.getBean("child1", Child.class);
        child1.eat();

        Child child2 = context.getBean("child2", Child.class);
        child2.eat();

        Child child3 = context.getBean("child3", Child.class);
        child3.eat();
    }
}
