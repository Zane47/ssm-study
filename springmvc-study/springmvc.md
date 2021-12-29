# 内容简介

| 内容           | 说明                         | 重要程度 |
| -------------- | ---------------------------- | -------- |
| Spring MVC入门 | Spring MVC开发流程与环境配置 | 5        |
| 接收Web数据    | Spring MVC参数接收与数据绑定 | 5        |
| URL Mapping    | 讲解URL绑定过程              | 5        |
| 中文乱码问题   | 解决请求与响应中文乱码       | 5        |
| 拦截器         | Spring MVC拦截器的使用       | 3        |

拦截器有点类似于j2ee中的过滤器, 但是比过滤器更加高级, 拦截器可以对来自client的请求做统一处理, 进而实现SpringMVC中的高级功能

---

Restful开发风格

| 内容                  | 说明                                | 重要程度 |
| --------------------- | ----------------------------------- | -------- |
| Restful风格介绍       | 介绍Restful开发规范                 | 5        |
| Restful开发实战       | 实例讲解Restful在Spring MVC中的实现 | 5        |
| JSON序列化            | 通过响应输出数据                    | 5        |
| Restful的**跨域**问题 | 分析跨域问题的来源与解决办法        | 5        |

# Spring MVC

## MVC

MVC: 架构模式

Model(模型), View(视图), Controller(控制器)

<img src="img/springmvc/image-20211228190749625.png" alt="image-20211228190749625" style="zoom:50%;" />

View: 界面, 与用户进行交互

Model: 数据, 业务逻辑部分

MVC中, 如果View中的数据来自于Model, 并不由View直接去调用获取到Model中的数据, 而是需要通过Controller作为中介. 

Controller是MVC中最重要的部分, 接收View中传入的数据, 根据数据调用后端的业务逻辑Model得到结果, 再通过Controller返回给View

MVC中View和Model没有直接的关系, 需要Controller来控制. Servlet就是Controller技术, 但是不太方便, 所以提供Spring MVC更加简化Web应用程序的开发

---

* Spring MVC是Spring体系的轻量级Web MVC框架. 用来替代j2e的Servlet, 让web开发更加简单
* Spring MVC的核心Controller控制器，用于处理请求，产生响应. View不允许直接调用Model, 需要Controller作为中间者来调用, 好处: 界面和后端业务逻辑解耦, 提高可维护性
* Spring MVC基于Spring IOC容器运行，所有对象被IOC管理

## Spring 5.X

* Spring 5.×最低要求JDK8与J2EE 7(Servlet 3.1/Tomcat 8.5+)
* Spring5.x支持JDK8/9,可以使用新特性
* Spring5.x最重要的新特性支持**响应式编程**

响应式编程: 根据事件来, 当触发了某个事件, 自动执行某段代码. 另外一种编程风格, 专门对事件做出响应的程序

## Spring MVC环境配置

[文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)

idea环境下创建Maven WebApp: 

### 将工程设置为web开发

1. 在Project Structure -> facets -> + web

<img src="img/springmvc/image-20211229152459600.png" alt="image-20211229152459600" style="zoom:67%;" />

2. 设置Web地址

<img src="img/springmvc/image-20211229153306716.png" alt="image-20211229153306716" style="zoom: 33%;" />

* 设置Deployment Descriptors

其中: 

Web Module Deployment Descriptor (web.xml): first-springmvc\src\main\webapp\WEB-INF\web.xml 

Deployment descriptor version : 3.1. SpringMVC中Servlet的最低版本就是3.1

* 设置Web Resource Directories(存储web页面地址)

Web Resource Directory: first-springmvc\src\main\webapp

3. 创建Facet

点击"Create Artifact"自动创建

![image-20211229153430445](img/springmvc/image-20211229153430445.png)

Artifact看成运行的方式

![image-20211229153610106](img/springmvc/image-20211229153610106.png)

Web Application: Exploded. 运行时使用目录的方式对web应用进行运行

如果改成Archive方式, 最后应用就会打包成war包来运行

开发环境下, 一般都选择Exploded

4. 添加html测试页

webapp下新增index.html页面, html5

```xml
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Hello, spring-mvc</h1>
</body>
</html>
```

---

启动项目, 展示网页

5. 配置tomcat

* Add Configuration -> Tomcat Server -> Local

tomcat版本至少8.5, -> apache-tomcat-8.5.40

![image-20211229154754259](img/springmvc/image-20211229154754259.png)

* 配置Application Server:

<img src="img/springmvc/image-20211229195430603.png" alt="image-20211229195430603" style="zoom:67%;" />

* 添加Deployment, 添加web应用

![image-20211229195556625](img/springmvc/image-20211229195556625.png)

这里有上下文设置, 对应到访问的URL: http://localhost:8080/first_springmvc_Web_exploded/

如果配置/, 那就是localhost

设置port为80: http://localhost:80/

![image-20211229200145296](img/springmvc/image-20211229200145296.png)

* 设置热部署

<img src="img/springmvc/image-20211229195711076.png" alt="image-20211229195711076" style="zoom:67%;" />

### Spring mvc具体配置

1. Maven依赖spring-webmvc

2. web.xml配置DispatcherServlet

3. 配置applicationContext的mvc标记

4. 开发测试Controller

---

1. maven添加spring-webmvc依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.1.9.RELEASE</version>
</dependency>
```

2. web.xml中添加配置DispatcherServlet(对所有请求进行拦截)

DispatcherServlet是Spring MVc最核心的对象, 用于拦截Http请求, 并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理. 中转站

类比前台, 接待客户, 接收邮件, 首先面向前台, 然后前台根据不同的情况联系内部不同的人来进行处理. 合同->法务部, 客户->市场部. 外部环境和内部人员的对接窗口, 所有的进出都需要通过前台完成. DispatcherServlet就是前台, Controller就是具体的部门处理人员

* web.xml中添加DispatcherServlet

```xml
<!-- DispatcherServlet -->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <!--
            DispatcherServlet是Spring MVc最核心的对象
            DispatcherServlet用于拦截Http请求，
            并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理
        -->

    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
```

* 添加servlet-mapping

```xml
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <!-- 写上url请求的根, 那么所有的请求都会通过DispatcherServlet来分发 -->
    <!-- / 代表拦截所有请求-->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

* DispatcherServlet添加额外的配置

load-on-startup: 在Web应用启动时自动创建Spring IoC容器, 并初始化DispatcherServlet.如果没有这句话, 会在第一次访问url的时候创建. 因为要初始化IOC容器, 所以DispatcherServlet需要知道applicationContext.xml的路径 -> contextConfigLocation

```xml
<!-- DispatcherServlet -->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <!--
            DispatcherServlet是Spring MVc最核心的对象
            DispatcherServlet用于拦截Http请求，
            并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理
        -->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

    <!-- applicationContext.xml -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </init-param>
    <!-- 额外的选项 -->
    <!--
            在Web应用启动时自动创建Spring IoC容器，
            并初始化DispatcherServlet.
            如果没有这句话, 会在第一次访问url的时候创建
            因为要初始化IOC容器, 所以DispatcherServlet需要知道applicationContext.xml的路径
        -->
    <load-on-startup>0</load-on-startup>
</servlet>
```

---

整体的web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <!-- DispatcherServlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <!--
            DispatcherServlet是Spring MVc最核心的对象
            DispatcherServlet用于拦截Http请求，
            并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理
        -->
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- applicationContext.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <!-- 额外的选项 -->
        <!--
            在Web应用启动时自动创建Spring IoC容器，
            并初始化DispatcherServlet.
            如果没有这句话, 会在第一次访问url的时候创建
            因为要初始化IOC容器, 所以DispatcherServlet需要知道applicationContext.xml的路径
        -->
        <load-on-startup>0</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <!-- 写上url请求的根, 那么所有的请求都会通过DispatcherServlet来分发 -->
        <!-- / 代表拦截所有请求-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

3. 添加applicationContext.xml, 注意需要配置mvc的命名空间: xmlns:mvc

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">
</beans>
```

启动注解模式, mvc注解. 排除静态资源

```xml
<!-- context:component-scan标签作用
        在Spring IoC初始化过程中，自动创生并营理com.imooc.springmvc及子包中
        拥有以下注解的对象.
        @Repository
        @Service
        @Controller
        @Component
    -->
<context:component-scan base-package="com.imooc.springmvc"/>

 <!-- 启用Spring MVC的注解开发模式 -->
<mvc:annotation-driven/>

<!-- 将图片/JS/CSs等静态资源排除在外，可提高执行效率 -->
<mvc:default-servlet-handler/>
```

mvc:default-servlet-handler. 当http请求发过来之后, springmvc发现是静态资源, 就不会处理

---

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- context:component-scan标签作用
        在Spring IoC初始化过程中，自动创生并营理com.imooc.springmvc及子包中
        拥有以下注解的对象.
        @Repository
        @Service
        @Controller
        @Component
    -->
    <context:component-scan base-package="com.imooc.springmvc"/>

    <!-- 启用Spring MVC的注解开发模式 -->
    <mvc:annotation-driven/>

    <!-- 将图片/JS/CSS等静态资源排除在外，可提高执行效率 -->
    <mvc:default-servlet-handler/>
</beans>
```

4. 开发Controller

类添加注解@Controller表明是Controller

@GetMapping: 将当前方法绑定某个get请求url

@ResponseBody: 直接向响应输出字符串数据，不跳转页面

```java
package com.imooc.springmvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 处理http请求, 并返回相应
 */
@Controller
public class TestController {
    // GetMapping: 将当前方法绑定某个get请求url
    @GetMapping("/t")
    @ResponseBody // 直接向响应输出字符串数据，不跳转页面
    public String test() {
        return "hello spring mvc";
    }
}
```

5. 调整tomcat

Edit Configuration -> Deployment -> Edit. 调整部署

默认情况下, 底层依赖的jar包不会自动放到当前的发布目录中, 需要手动将maven依赖放入.

![image-20211229212649274](img/springmvc/image-20211229212649274.png)

回到Deployment, Application Context配置/即可, 简化web的访问, 默认路径为: http://localhost:8080/

![image-20211229212851549](img/springmvc/image-20211229212851549.png)

6. 测试

直接在浏览器地址中输入: http://localhost:8080/t

@GetMapping注解将test方法绑定到/t的请求响应

只需要在方法上添加注解就可以完成web请求的处理和返回

---

示意图:

<img src="img/springmvc/image-20211229212355518.png" alt="image-20211229212355518" style="zoom:67%;" />

`URL-Pattern:/`: 拦截所有请求, 请求会被发送到DispatcherServlet -> 当前访问地址是/t, 在方法中找哪个方法上映射了/t -> test方法处理请求 -> return String -> 返回响应

## Controller





































# RESTful



























# SpringMVC拦截器



-> 过滤器























































