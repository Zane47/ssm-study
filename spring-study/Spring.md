# 内容介绍

imooc体系课[Java工程师](https://class.imooc.com/java2021#Anchor)Spring部分笔记

## Spring IOC

| 内容              | 说明                          | 重要程度 |
| ----------------- | ----------------------------- | -------- |
| Spring 框架介绍   | Spring IOC、DI和AOP等核心概念 | 5        |
| Spring loC容器    | Spring实例化与管理对象        | 5        |
| 集合对象注入      | 注入List、Set、Map集合对象    | 5        |
| 底层原理          | Spring Bean的生命周期         | 5        |
| 注解与Java Config | Spring注解分类和常用注解应用  | 5        |

## Spring AOP

| 内容          | 说明                               | 重要程度 |
| ------------- | ---------------------------------- | -------- |
| 理解AOP及名词 | Spring AOP开发与配置流程           | 4        |
| 五种通知类型  | Spring五种通知类型与应用场景       | 3        |
| 切点表达式    | PointCut切点表达式的语法规则及应用 | 2        |
| 代理模式      | JDK动态代理和CGLib代理的执行过程   | 4        |

## Spring JDBC与声明式事务

| 内容               | 说明                              | 重要程度 |
| ------------------ | --------------------------------- | -------- |
| Spring JDBC        | Spring JDBC的环境配置             | 4        |
| RestTemplate       | 基于RestTemplate实现SQL处理(CRUD) | 3        |
| 配置声明式事务     | 声明式事务的配置过程              | 5        |
| 事务传播行为介绍   | 讲解常用事务传播行为的用途        | 3        |
| 声明式事务注解形式 | 基于注解使用声明式事务            | 5        |

事务传播行为: 当多个拥有事务的方法彼此嵌套调用的时候, 事务是如何进行控制的


# Spring

## 什么是Spring

Spring可从狭义与广义两个角度看待: 

狭义的Spring是指Spring框架(Spring Fremework): 高度的封装和抽象技术, 简化程序开发, 底层提供IoC容器管理系统中的对象和依赖

广义的Spring是指Spring生态体系: 

### 狭义的Spring

* Spring框架是企业开发复杂性的一站式解决方案

* Spring框架的核心是**IoC容器**与**AOP面向切面编程**

IoC: 所有对象管理的基础, 包括AOP

* Spring loC负责创建与管理系统对象，使用者从IoC容器中提取对象, 所以Spring可以在此基础上扩展功能, 例如AOP, 在方法前后扩展

---

[Spring Framework: ](https://docs.spring.io/spring-framework/docs/5.0.0.RC2/spring-framework-reference/overview.html)

![spring overview](https://docs.spring.io/spring-framework/docs/5.0.0.RC2/spring-framework-reference/images/spring-overview.png)

四层

* Core Container: 最核心, IOC容器, pom中引入的context就是该模块. 把这个模块引入以后，作为spring依赖关系，会把对应的号核心模块进行引入，它提供了spring最核心的代码实现以及Beans; Beans对对象进行创建和装配; SpEL是spring的表达式语言

* TestS: Spring 提供的测试模块

再晚上是Spring的性能增强以及网络, 以上的模块都基于IOC容器来实现

* aspect: 切面; instrumentation: 检测在运行过程Spring; Message: spring提供的消息处理功能

* Data Access: 数据访问, ORM: 访问ORM框架, OXM: 数据访问框架, 提供了Java对象和xml文档之间的互相转换。Transaction: 事务处理
* Web: 


### 广义的Spring

生态体系, 一站式解决方案

分布式, 微服务, 响应式编程, 云端技术(自动扩展自动链接), web apps, serverless无服务器编程, 事件驱动, 批处理...

### 传统开发方式

对象直接引用导致对象硬性关联，程序难以扩展维护. 使用者主动创建对象

使用者 -> new ObjectA -> new ObjectB

### 使用Spring IoC

Spring IoC容器是Spring生态的地基，用于统一创建与管理对象依赖

<img src="img/Spring/image-20211220154255025.png" alt="image-20211220154255025" style="zoom: 67%;" />

注入: 使用反射技术, 将ObjectA的依赖B, 注入到ObjectA中 -> DI

使用者不需要关注容器内部的对象和对象之间的关系, 只需要关注从什么地方提取需要的对象即可. 使用者不再面对具体的对象, 而是面对容器, 通过容器获取到需要的对象

>  SpringIoC容器职责:
>
> * 对象的控制权交由**第三方**统一管理（loC控制反转）
> * 利用Java**反射**技术实现**运行时**对象创建与关联（Dl依赖注入）
> * 基于配置提高应用程序的可维护性与扩展性

# Spring IoC

## IoC与DI

Spring快速入门, Spring XML配置, 依赖注入配置, 对象实例化配置, 注解与Java Config, Spring 单元测试

### 控制反转

**控制反转（Inverse of Control）**是一种是面向对象编程中的一种设计理念，用来减低计算机代码之间的耦合度。其基本思想是：借助于“第三方”实现具有依赖关系的对象之间的解耦。

并不是由最终的消费者负责创建对象, 引入"代理人"的角色, 由"代理人"统一对象的创建和管理, 消费者面向"代理人"获取对象和进行信息的交换与处理

IoC根本目的: 为了降低对象之间的直接耦合.通过代理人来做解耦, 对象之间可以灵活变化

加入**IoC容器**将对象统一管理，让对象关联变为弱耦合

### DI依赖注入

* IoC是设计理念，是现代程序设计遵循的标准，是宏观目标

* DI(Dependency Injection)是具体技术实现，是微观实现; 在程序**运行过程**中完成对象的创建与绑定.

运行过程 -> DI在Java中利用**反射**技术实现对象注入(Injection), 反射技术来做行为的动态变化, 例如:创建对象, 调用属性, 调用方法

## Spring IoC初体验

### 传统写法

```java
Apple apple1 = new Apple("apple1", "red", "origin1");
Apple apple2 = new Apple("apple2", "green", "origin2");
Apple apple3 = new Apple("apple3", "yellow", "origin3");
// 孩子和苹果之间的对象关联
Child child1 = new Child("child1", apple1);
Child child2 = new Child("child2", apple2);
Child child3 = new Child("child3", apple3);
child1.eat();
child2.eat();
child3.eat();
```

弊端:

* 对象的描述写死在代码中, 对象的信息发现变化后需要修改源代码->不合理, 写死之后修改, 应用环境就需要重新发布上线, 要重新提交测试审核等流程, 软件的维护性很差
* 对象的数量写死了, 如果要新增对象, 要修改源代码
* 最重要的一点: 对象是硬关联, 在程序中使用构造方法参数进行设置, 程序运行后两个对象(孩子和苹果)的关系就确定了, 在程序编译时就确定了关系, 死板, 例如两个孩子吃的苹果交换一下, 就需要修改源代码. 
  * new 关键字在编译时对象与对象之间强制绑定

-> Spring IOC: 根本目的就是通过**配置**的方式完成对象的实例化以及对象之间的依赖关系

### 修改成IoC写法

1. 添加pom依赖

```xml
 <!-- 使用spring ioc容器最小的范围 -->
 <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-context</artifactId>
     <version>5.2.6.RELEASE</version>
 </dependency>
```

2. 新建applicationContext.xml文件

参考[官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core) 1.2.1Configuration Metadata中的xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--在Ioc容器启动时，自动由Spring实例化Apple对象，取名sweetApple放入到容器中-->
    <bean id="sweetApple" class="com.imooc.spring.ioc.entity.Apple">
        <property name="title" value="红富士"/>
        <property name="color" value="Red"/>
        <property name="origin" value="Eur"/>
    </bean>

    <bean id="sourApple" class="com.imooc.spring.ioc.entity.Apple">
        <property name="title" value="green apple"/>
        <property name="color" value="Green"/>
        <property name="origin" value="origin2"/>
    </bean>

    <bean id="softApple" class="com.imooc.spring.ioc.entity.Apple">
        <property name="title" value="金帅"/>
        <property name="color" value="Red"/>
        <property name="origin" value="China"/>
    </bean>
</beans>
```

* 可以将原本程序中写死的属性, 写到配置文件中. 配置文件是纯文本, 内容变化后不需要对程序进行重新编译
* 在xml中写的bean, 在spring启动的时候自动由Spring实例化该对象, 取名id放到IoC容器中 -> 这里容器会自动创建三个对象

3. SpringApplication

```java
import com.imooc.spring.ioc.entity.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        //创建SpringIoC容器，并根据配置文件在容器中实例化对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Apple sweetApple = context.getBean("sweetApple", Apple.class);
        System.out.println(sweetApple.getTitle());
    }
}
```

---

* IoC容器可以通过xml文件的方式, 让我们不再使用new关键字来创建对象, 对于每一个创建的对象都放到了IoC容器中

, IoC统一管理, 贴上标签Bean id. 

![container magic](https://docs.spring.io/spring-framework/docs/current/reference/html/images/container-magic.png)

* 代码变成可配置文本, 无需修改源代码, 只需要修改配置文件

* 通过配置方式维护对象之间的关联关系, 不需要修改源代码 -> Child和Apple之间的关系

4. 添加child的bean id, ref标签

```xml
<bean id="child1" class="com.imooc.spring.ioc.entity.Child">
    <property name="name" value="child1"/>
    <property name="apple" ref="sweetApple"/>
</bean>
<bean id="child2" class="com.imooc.spring.ioc.entity.Child">
    <property name="name" value="child2"/>
    <property name="apple" ref="sourApple"/>
</bean>
<bean id="child3" class="com.imooc.spring.ioc.entity.Child">
    <property name="name" value="child3"/>
    <property name="apple" ref="softApple"/>
</bean>
```

ref标签指向其他的bean

5. 取出bean

```java
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
```

* 代码上来看, 没有描述任何对象实例化和对象关联的操作, Spring IoC容器在启动过程中根据配置文件applicationContext.xml进行动态的初始化和绑定, 用到的是java中反射的技术, 在程序运行时完成

* 虽然增加了配置, 但是提高了程序上线的可维护性, 如果修改对象关系或者对象初始化, 原始的方式需要修改源代码, 在spring ioc中直接修改配置文件, 不需要修改源代码

* Spring IoC容器的用途, 对象与对象之间解耦, IoC容器对所有的对象做统一的管理, 对象之间的关联也通过反射技术进行管理, 在**运行时**动态设置, 灵活. 以前的对象关系通过硬编码形式(编译时确定), 现在用配置文件(运行时反射)

## xml管理对象, Bean

Java Bean, java可重用对象的编码要求, 例如: 必须有默认构造函数, 属性私有, 通过gettersetter方法设置属性..., 满足这些条件就可以成为Java Bean. 而在Spring IOC容器中管理的就是这些java Bean, 容器中的对象

### 三种配置方式

* 基于XML配置Bean
* 基于注解配置Bean
* 基于Java代码配置Bean

三种配置方式都是告诉SpringIOC容器如何实例化, 如何管理Bean, 只是表现形式不同

#### 基于xml配置Bean

实例化Bean的三种方式:

* 基于构造方法对象实例化(90%的情况)
* 基于静态工厂实例化
* 基于工厂实例方法实例化

##### 基于构造方法对象实例化

###### 无参初始化

* xml文件中添加

```xml
<!--在Ioc容器启动时，自动由Spring实例化Apple对象，取名sweetApple放入到容器中-->
<!--
    bean: 通知IOC容器需要实例化什么对象
    class: 从哪个类进行实例化
-->
<!-- <Bean>标签默认通过默认构造方法创建对象 -->
<bean id="apple1" class="com.imooc.spring.ioc.entity.Apple"/>
```

* 查看加载到容器中

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        //创建SpringIoC容器，并根据配置文件在容器中实例化对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
}
```

ApplicationContext是接口, 有各种实现类, ClassPathXmlApplicationContext从置顶路径加载xml配置文件

执行后java运行内存中保存了IOC容器, 并且IOC容器根据xml中的配置创建对应的对象, 并管理

运行后, 实例化和初始化对象

给Apple添加无参的初始化

```java
Apple() {
    System.out.println("apple 对象已创建" + this);
}
```

直接运行可以看到输出:

```
apple 对象已创建com.imooc.spring.ioc.entity.Apple@737996a0
```

所以说,**`<Bean>`标签默认通过默认构造方法创建对象**

###### 含参初始化

1. xml文件配置

* constructor-arg name: 使用**参数名**的方式给对应的参数进行初始化赋值

```xml
<!-- 含参初始化 -->
<bean id="sweetApple" class="com.imooc.spring.ioc.entity.Apple">
    <!--没有constructor-arg则代表调用默认构造方法实例化-->
    <constructor-arg name="title" value="红富士"/>
    <constructor-arg name="color" value="红色"/>
    <constructor-arg name="origin" value="欧洲"/>
</bean>
```

constructor-arg会去查找在class中对应的构造方法, 再根据name参数的名字, 使用反射的技术在运行时动态设置对应的值.

* constructor-arg index: 利用构造方法**参数位置**实现对象实例化

```xml
<bean id="sweetApple2" class="com.imooc.spring.ioc.entity.Apple">
    <!--利用构造方法参数位置实现对象实例化-->
    <constructor-arg index="0" value="红富士"/>
    <constructor-arg index="1" value="红色"/>
    <constructor-arg index="2" value="欧洲"/>
</bean>
```

2. 书写构造函数输出

```java
public Apple(String title, String color, String origin) {
    this.title = title;
    this.color = color;
    this.origin = origin;
    System.out.println("带参数的构造函数创建对象: " + this);
    System.out.println(this.title + " " + this.color + " " + this.origin);
}
```

直接运行Application

```
带参数的构造函数创建对象: com.imooc.spring.ioc.entity.Apple@548e7350
红富士 红色 欧洲
```

3. 其他类型初始化

xml文件中虽然是以字符类型, 但是spring框架会把字符型自动转成对应的类型, 例如这里添加price为double类型

```xml
<bean id="sweetApple1" class="com.imooc.spring.ioc.entity.Apple">
    <!--没有constructor-arg则代表调用默认构造方法实例化-->
    <constructor-arg name="title" value="红富士"/>
    <constructor-arg name="color" value="红色"/>
    <constructor-arg name="origin" value="欧洲"/>
    <constructor-arg name="price" value="12.55"/>
</bean>
```

Apple中添加对应的构造函数

```java
public Apple(String title, String color, String origin, double price) {
    this.title = title;
    this.color = color;
    this.origin = origin;
    this.price = price;
    System.out.println("带参数的构造函数创建对象: " + this);
    System.out.println(this.title + " " + this.color + " " + this.origin + " " + this.price);
}
```

输出:

```
带参数的构造函数创建对象: com.imooc.spring.ioc.entity.Apple@1ed4004b
红富士 红色 欧洲 12.55
```

4. 如果没有对应的构造方法

编译提示错误:

<img src="img/Spring/image-20211221231549289.png" alt="image-20211221231549289" style="zoom:50%;" />

idea会报错:

```
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'sweetApple1' defined in class path resource [applicationContext.xml]: Unsatisfied dependency expressed through constructor parameter 2: Ambiguous argument values for parameter of type [java.lang.String] - did you specify the correct bean references as arguments?
```

所以xml配置要严格按照类的定义来书写

##### 基于静态工厂实例化

工厂模式: 隐藏创建类的细节, 通过额外的工厂类组织创建需要的对象

静态工厂: 静态工厂通过静态方法(static)创建对家，隐藏创建对象的细节

1. 新建AppleStaticFactory

```java
import com.imooc.spring.ioc.entity.Apple;

/**
 * 静态工厂通过静态方法创建对家，隐藏创建对象的细节
 */
public class AppleStaticFactory {
    public static Apple createSweetApple() {
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}
```

2. xml文件中

```xml
<!-- 利用静态工厂获取对象 -->
<bean id="apple4" class="com.imooc.spring.ioc.factory.AppleStaticFactory"
      factory-method="createSweetApple"/>
```

产生的apple对象放到了Spring IOC容器中

* 疑惑: 在静态工程中, 不还是使用的new关键字创建Apple对象? 这样子和直接在程序中new Apple有什么区别?

这里工厂通过静态方法创建对象, 同时隐藏创建对象的细节, 只看方法的话, 内部创建的过程是不可见的, 工厂的目的就是为了不让我们知道对象是如何构建的. 对创建者来说, 只需要调用了`createSweetApple`方法, 工厂就会返回一个sweetApple对象

出现静态工厂的目的: 在IOC容器之外, 通过程序的方式来组织对象, 

好处: 如果在createSweetApple方法中添加额外的行为, 例如logger, 与实际业务相关, 增加额外的代码. 这些工作对于纯xml配置的方式就较难实现

##### 基于工厂实例方法实例化

静态方法, static关键字描述, 说明这个方法(createSweetApple)属于工厂类(AppleStaticFactory)本身, 不属于工厂对象

工厂实例: 在IOC容器中对工厂进行实例化, 调用工厂对象中的某一个方法来完成具体对象创建的工作

工厂实例方法创建对象是指: IoC容器对工厂类进行实例化并调用对应的实例方法创建对象的过程

1. 新建AppleFactoryInstance

没有static关键字, 说明方法属于工厂对象, 而不是属于工厂类

```java
import com.imooc.spring.ioc.entity.Apple;
/**
 * 工厂实例方法创建对象是指IoC容器对工厂类进行实例化并调用对应的实例方法创建对象的过程
 */
public class AppleFactoryInstance {
    public Apple createSweetApple() {
        Apple apple = new Apple();
        apple.setTitle("红富士");
        apple.setOrigin("欧洲");
        apple.setColor("红色");
        return apple;
    }
}
```

2. xml文件中

需要两个Bean, 第一个Bean指向工厂本身, 第二个Bean利用工厂实例的createSweetApple方法获取实例对象, 使用factory-bean和factory-method

```xml
<!-- 利用工厂实例获取对象 -->
<!-- 在IOC容器初始化中, 首先对工厂进行实例化 -->
<bean id="factoryInstance" class="com.imooc.spring.ioc.factory.AppleFactoryInstance"/>
<bean id="apple5" factory-bean="factoryInstance" factory-method="createSweetApple"/>
```

两种工厂本质都是封装隐藏创建对象的细节.

#### 基于注解配置Bean















#### 基础Java代码配置Bean



### IOC容器中获取Bean

#### 获取方法

```java
Apple sweetApple = context.getBean("sweetApple", Apple.class);// 优先
Apple sweetApple1 = (Apple) context.getBean("sweetApple");
```

#### bean id和name

name用来设置bean名称

id和name的相同点: 

* bean id与name都是设置对象在loC容器中唯一标识
* 两者在同一个配置文件中都不允许出现重复
* 两者允许在**多个配置**文件中出现**重复**, 新对象覆盖旧对象

同一个文件不允许重复, 多个文件允许重复, 新对象覆盖旧对象

id和name的区别:

* id要求更为严格，一次只能定义一个对象标识（推荐）
* name更为宽松，一次允许定义多个对象标识
* tips:id与name的命名要求有意义,按驼峰命名书写

##### 多个applicationContext文件

1. 复制一个applicationContext-1.xml文件

2. ClassPathXmlApplicationContext允许加载多个xml文件

```java
String[] configLocatoins = new String[]{"classpath:applicationContext.xml", "classpath:applicationContext-1.xml"};

//创建SpringIoC容器，并根据配置文件在容器中实例化对象
ApplicationContext context =
    new ClassPathXmlApplicationContext(configLocatoins);

Apple sweetApple = context.getBean("sweetApple", Apple.class);
System.out.println(sweetApple.getTitle());
```

按照String[]中的顺序, 先加载applicationContext, 再加载了applicationContext-1, 覆盖了旧对象

##### id的标识唯一, name更为宽松，一次允许定义多个对象标识

```xml
<bean name="sweetApple, apple7" class="com.imooc.spring.ioc.entity.Apple">
    <!--没有constructor-arg则代表调用默认构造方法实例化-->
    <constructor-arg name="title" value="红富士2"/>
    <constructor-arg name="color" value="红色"/>
    <constructor-arg name="origin" value="欧洲"/>
</bean>
```

可以通过`getBean("apple7", Apple.class)`来得到

通过name属性可以设置多个bean标识, id只能设置一个bean标识

##### 没有id与name的bean默认使用类名全称作为bean标识

开发中用得少

```xml
<!--没有id与name的bean默认使用类名全称作为bean标识-->
<bean class="com.imooc.spring.ioc.entity.Apple">
    <constructor-arg name="title" value="红富士3"/>
    <constructor-arg name="color" value="红色3"/>
    <constructor-arg name="origin" value="欧洲3"/>
</bean>
```

```java
Apple apple = context.getBean("com.imooc.spring.ioc.entity.Apple", Apple.class);
System.out.println(apple.getTitle());
```

### 路径匹配表达式

IOC加载配置使用的路径匹配表达式

```java
//创建SpringIoC容器，并根据配置文件在容器中实例化对象
ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml")
    
// 加载多配置文件
String[] configLocatoins = new String[]{"classpath:applicationContext.xml", "classpath:applicationContext-1.xml"};

//创建SpringIoC容器，并根据配置文件在容器中实例化对象
ApplicationContext context =
    new ClassPathXmlApplicationContext(configLocatoins);
```

`classpath:applicationContext.xml`: 加载类路径下, 名为applicationContext的配置文件

类路径: target/classes目录, 而不是源代码中的resources

<img src="img/Spring/image-20211222164606730.png" alt="image-20211222164606730" style="zoom: 80%;" />

---

其他的写法:

| 表达式实例                      | 说明                                                |
| ------------------------------- | --------------------------------------------------- |
| classpath:config.xml            | 扫描classpath根路径(不包含jar)的config.xml          |
| classpath:com/imooc/config.xml  | 扫描classpath下(不包含jar)com.imooc包中的config.xml |
| classpath*:com/imooc/config.xml | 扫描classpath下(包含jar)com.imooc包中的config.xml   |
| classpath:config-*.xml          | 扫描classpath根路径下所有以config-开头的XML文件     |
| classpath:com/**/config.xml     | 扫描com包下（包含任何子包）的config.xml             |
| file:c:/config.xml              | 扫描c盘根路径config.xml                             |

* 不包含jar: 只扫描自己写的target中的资源或配置文件, 因为引入的maven依赖jar包本质也是压缩包, 其中也有可能包含配置文件

* config-*.xml: 多配置文件情况下, 后加载的覆盖旧加载的, ` *通配符 `的加载顺序按照文件名ASCNII码升序.

### 对象依赖注入

在SpringIOC容器中设置对象的依赖关系, 也就是对象依赖注入过程

之前Apple和Child之间的例子:

<img src="img/Spring/image-20211223091428315.png" alt="image-20211223091428315" style="zoom:67%;" />

依赖注入是指运行时将容器内对象利用**反射**赋给其他对象的操作, 两种实现方法:

* 基于setter方法注入对象(开发中常用) ->两种 使用场景
* 基于构造方法注入对象

#### 基于setter方法注入对象

##### 利用setter实现静态数值的注入

1. 属性使用property标签指代, name: 属性名, value: 属性值

在数据操作的时候, 所有的都是对象, String, Double, Float, 包装类型

```xml
<!--IoC容器自动利用反射机制运行时调用stXXX方法为属性赋值-->
<bean id="sweetApple" class="com.imooc.spring.ioc.entity.Apple">
    <property name="title" value="红富士"/>
    <property name="color" value="Red"/>
    <property name="origin" value="Eur"/>
</bean>
```

***IOC容器内对象会使用反射技术, 在运行时调用setXXX方法为属性赋值***

2. 在Apple的setTitle方法中新增输出:

```java
public void setTitle(String title) {
    System.out.println("hello");
    this.title = title;
}
```

输出:

```
hello
红富士
```

3. Apple中新增属性

```java
private Double price;
```

新增属性price, 但是不设置getter和setter方法, 变异有错误提示, 运行报错:

```
警告: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sweetApple' defined in class path resource [applicationContext.xml]: Error setting property values; nested exception is org.springframework.beans.NotWritablePropertyException: Invalid property 'price' of bean class [com.imooc.spring.ioc.entity.Apple]: Bean property 'price' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sweetApple' defined in class path resource [applicationContext.xml]: Error setting property values; nested exception is org.springframework.beans.NotWritablePropertyException: Invalid property 'price' of bean class [com.imooc.spring.ioc.entity.Apple]: Bean property 'price' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
```

##### setter方法注入对象(对象与对象之间的依赖)

ref后写有效的bean id, 完成apple和child关联

1. 增加配置

```xml
<bean id="sweetApple" class="com.imooc.spring.ioc.entity.Apple">
    <property name="title" value="红富士"/>
    <property name="color" value="red"/>
    <property name="origin" value="eur"/>
    <property name="price" value="12.2"/>
</bean>

<!-- -->
<bean id="lily" class="com.imooc.spring.ioc.entity.Child">
    <property name="name" value="莉莉"/>
    <property name="apple" ref="sweetApple"/>
</bean>
```

2. java类中添加输出

```java
public Apple() {
    System.out.println("apple 对象已创建" + this);
}
```

```java
public Child() {
    System.out.println("创建child对象: " + this);
}

public void setApple(Apple apple) {
    System.out.println("setApple: " + apple);
    this.apple = apple;
}
```

运行后发现, SpringIOC容器初始化:

```
apple 对象已创建com.imooc.spring.ioc.entity.Apple@735b478
Apple setTitle: 红富士
创建child对象: com.imooc.spring.ioc.entity.Child@69ea3742
setApple: com.imooc.spring.ioc.entity.Apple@735b478
```

可以看到, Child中注入的Apple对象正是之前创建的Apple(@735b478)

在创建过程中, 在IOC容器中创建了两个不相关的对象Apple和Child, IOC容器调用Child对象的setApple方法将之前创建的Apple对象赋值set的参数, 赋值Child中的Apple属性

#### 注入集合对象

注入List:

```xml
<bean id="..." class="...">
	<property name="someList">
	<list>
		<value>具体值</value>
		<!-- 或者使用 -->
		<ref bean="beanid"></ref>
	</list>
	</property>
</bean>
```

注入Set:

```xml
<bean id="..." class="...">
	<property name="someSet">
	<set>
		<value>具体值</value>
        <!-- 或者使用 -->
		<ref bean="beanld"></ref>
	</set>
	</property>
</bean>
```

注入Map：

```xml
<bean id="..." class="...">
	<property name="someMap">
	<map>
		<entry key="k1" value="v1"></entry>
		<entry key="k2" value-ref="beanid"></entry >
	</map>
	</property>
</bean>
```

注入Properties, 属性类型: 

```xml
<bean id="..." class="...">
	<property name="someProperties">
	<props>
		<prop key="k1">v1</prop>
		<prop key="k2">v2</prop>
	</props>
	</property>
</bean>
```

与map区别: properties只允许key和value是字符串类型

---

例子:->s04

```xml
```









1. 可以看到ApplicatoinContext中List的默认注入类型是ArrayList

![image-20211223141835884](img/Spring/image-20211223141835884.png)



2. Set标签去重, LinkedHashSet, TreeSet是无需的, 但是linkedHashSet在内存中数据分散存储, 但是基于双向链表, 数据在提取的时候按照数据存放的顺序.

<img src="img/Spring/image-20211223143030351.png" alt="image-20211223143030351" style="zoom:67%;" />

3. Map标签, 可以直接ref来指向外部的bean, 但是麻烦; 也可以直接写内置bean, 只给该map使用

Map的类型是LinkedHashMap, 双向链表, 数据遍历提取的时候按照数据存放的顺序

![image-20211223143012475](img/Spring/image-20211223143012475.png)

4. property, 

```java
company.getInfo().getProperty("key")
```









#### 方法注入对象











#### 依赖注入的好处

利用IOC容器将软件过程中的**对象解耦**, 进而让软件成员协作也解耦. 双方约定bean的依赖, 互相不关系各自的实现, 不需要修改

1. 新建book-shop项目, 新建两个xml

service和dao两个xml由两个成员分别开发, 各自维护自己的模块

applicationContext-service: 保存所有的服务类

applicationContext-dao: 保存所有的数据服务类, CRUD

2. 新建dao和service类

* 新建接口

``` java
public interface BookDao {
    /**
     * 向book表中插入数据
     */
    public void insert();
}
```

* 新建实现类

```java
public class BookDaoImpl implements BookDao {
    @Override
    public void insert() {
        System.out.println("MySQL Table Book insert one record");
    }
}
```

* 新建Service

```java
import com.imooc.spring.ioc.bookshop.dao.BookDao;

public class BookService {

    // 不需要new, 在IOC容器启动过程中会自动注入
    private BookDao bookDao;
    /**
     * 采购新书
     */
    public void purchase() {
        System.out.println("purchase start");
        bookDao.insert();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```

BookService中使用BookDao, 注意不需要new, 让IOC容器启动的时候自动完成注入

3. 分别配置xml文件, 注意由两个人开发

applicationContext-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="bookDao" class="com.imooc.spring.ioc.bookshop.dao.BookDaoImpl">
    </bean>
</beans>
```

applicationContext-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="bookService" class="com.imooc.spring.ioc.bookshop.service.BookService">
        <!-- id=bookDao, 由另一位开发者开发 -->
        <property name="bookDao" ref="bookDao"/>
    </bean>
</beans>
```

4. 新增Application启动

```java
import com.imooc.spring.ioc.bookshop.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");

        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.purchase();
    }
}
```

输出:

```
purchase start
MySQL Table Book insert one record
```

---

通过配置的方式, 实现service调用dao的过程, service和dao是不同人开发, 都有自己的配置文件, 双方约定好依赖的bean之后, 独自开发, 不干扰

如果系统升级, MySQL升级为Oracle

1. 新增Oracle的impl类

```java
public class BookDaoOracleImpl implements BookDao {
    @Override
    public void insert() {
        System.out.println("Orcale table Book insert one revord");
    }
}
```

2. xml中bean的class修改为BookDaoOracleImpl

```xml
<bean id="bookDao" class="com.imooc.spring.ioc.bookshop.dao.BookDaoOracleImpl">
</bean>
```

对于service的开发者而言, 没有任何的影响, 不需要做任何修改(都是BookDao接口), 因为双方已经提前约定好了bean的依赖关系, 双方解耦

service端不需要修改任何数据

运行结果: 可以看到发生了变化

```
purchase start
Orcale table Book insert one revord
```



















## 查看容器内对象

实用技巧









## Bean scope





















## 基于注解与Java Config配置IoC容器























# Spring AOP

















# Spring JDBC与事务管理
















# Ref

imooc:[Java工程师]( https://class.imooc.com/java2021#Anchor)







