package com.imooc.spring.service;

import com.imooc.spring.dao.IUserDao;
import com.imooc.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("prototype")// 设置单例/多丽, xml中的bean scope相同
public class UserService {

    @Value("${metaData}") // 读取config.properties的metaData属性值
    private String metaData;

    // @Autowired
    // Spring Ioc容器会自动通过反射技术将属性private修饰符自动改为public,直接进行赋值
    // 不再执行set方法
    // private UserDao userDao;

    @Autowired
    private IUserDao uDao;

    @PostConstruct// 相当于xml中的init-method
    public void init() {
        System.out.println("UserService: init method, metaData = " + metaData);
    }

    public UserService() {
        System.out.println("UserService constructor " + this);
    }

    /*@Autowired
    // 如果装配注解Autowired放在set方法上，则自动按类型/名称对set方法参数进行注入
    public void setUserDao(UserDao userDao) {
        System.out.println("setUserDao: " + userDao);
        this.userDao = userDao;
    }*/

    public IUserDao getUserDao() {
        return uDao;
    }

}
