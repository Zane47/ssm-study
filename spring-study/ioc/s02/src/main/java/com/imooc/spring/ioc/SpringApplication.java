package com.imooc.spring.ioc;

import com.imooc.spring.ioc.entity.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        String[] configLocatoins = new String[]{"classpath:applicationContext.xml", "classpath:applicationContext-1.xml"};

        //创建SpringIoC容器，并根据配置文件在容器中实例化对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext(configLocatoins);

        Apple sweetApple = context.getBean("apple7", Apple.class);
        System.out.println(sweetApple.getTitle());

        /*ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
*/
        Apple apple4 = context.getBean("apple4", Apple.class);// 优先
        System.out.println(apple4.getTitle());

        Apple sweetApple2 = (Apple) context.getBean("sweetApple2");
        System.out.println(sweetApple2.getTitle());

        Apple apple = context.getBean("com.imooc.spring.ioc.entity.Apple", Apple.class);
        System.out.println(apple.getTitle());
    }
}
