package com.imooc.spring.ioc.factory;

import com.imooc.spring.ioc.entity.Apple;

/**
 * 静态工厂通过静态方法创建对家，隐藏创建对象的细节
 */
public class AppleStaticFactory {
    /**
     * 苹果的组装和创建
     */
    public static Apple createSweetApple() {
        // logger.info("");
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");

        return apple;
    }


}
