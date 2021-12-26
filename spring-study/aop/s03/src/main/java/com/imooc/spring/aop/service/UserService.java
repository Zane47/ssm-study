package com.imooc.spring.aop.service;

import com.imooc.spring.aop.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public void createUser() {
        /*if (1 == 1) {
            throw new RuntimeException("用户已存在");
        }*/


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("UserService, createUser()");
        userDao.insert();
    }

    public String generateRandomPassword(String type, Integer length) {
        System.out.println("按" + type + "方式生成" + length + "位随机密码");
        return "Zxcquei1";
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
