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

MVC: 架构模式

Model(模型), View(视图), Controller(控制器)

<img src="img/springmvc/image-20211228190749625.png" alt="image-20211228190749625" style="zoom:50%;" />

MVC中view不允许直接访问后端model











---

* Spring MVC是Spring体系的轻量级Web MVC框架
* Spring MVC的核心Controller控制器，用于处理请求，产生响应
* Spring MVC基于Spring IOC容器运行，所有对象被IOC管理





## Spring 5.X

* Spring 5.×最低要求JDK8与J2EE 7(Servlet 3.1/Tomcat 8.5+)
* Spring5.x支持JDK8/9,可以使用新特性
* Spring5.x最重要的新特性支持**响应式编程**

响应式编程: 根据事件来, 当触发了某个事件, 自动执行某段代码





















# RESTful



























# SpringMVC拦截器



-> 过滤器
























































