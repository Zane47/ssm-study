package com.imooc.spring.ioc.context;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 理解成, 每一个ClassPathXmlApplicationContext都对应一个IOC容器
 * 使用Map保存beanId和对象之间的关系
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    // IOC容器
    private Map iocContainer = new HashMap();

    /**
     * 默认构造方法
     * 读取配置文件
     */
    public ClassPathXmlApplicationContext() {
        try {
            String filePath = this.getClass().getResource("/applicationContext.xml").getPath();
            // 地址如果有中文, 需要url解码, 担心找不到
            filePath = URLDecoder.decode(filePath, "UTF-8");

            // 解析xml文件, 依赖org.dom4j和jaxen



        } catch(Exception e) {

        }
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
