package com.imooc.spring.aop.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * InvocationHandler是JDK提供的反射类，用于在JDK动态代理中对目标方法进行增强
 * InvocationHandler实现类与切面类的环绕通知类似
 */
public class ProxyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    private ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 在invoke()方法对目标方法进行增强
     *
     * @param proxy  代理类对象, 通常有jdk动态代理自动生成
     * @param method 目标方法对象
     * @param args   目标方法实参
     * @return 目标方法运行后返回值
     * @throws Throwable 目标方法抛出的异
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 扩展方法
        System.out.println("======= " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " =======");

        // 调用目标对象方法, 类似于环绕通知的ProceedingJointPoint.proceed()方法
        Object ret = method.invoke(target, args);

        return ret;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler(userService);

        // 动态创建代理类. 基于接口创建制定的代理类
        // 传入: 类加载器, 需要实现的接口, 如何对方法进行扩展
        // 这个运行时的效果和之前的静态代理的方式完全相同, 只不过是自动生成
        UserService userServiceProxy =
                (UserService) Proxy.newProxyInstance(
                        userService.getClass().getClassLoader(),
                        userService.getClass().getInterfaces(), invocationHandler);
        userServiceProxy.createUser();

        // 动态代理必须实现接口才可以运行
        EmployeeService employeeService = new EmplyeeServiceImpl();
        EmployeeService employeeServiceProxy =
                (EmployeeService) Proxy.newProxyInstance(
                        employeeService.getClass().getClassLoader(),
                        employeeService.getClass().getInterfaces(),
                        new ProxyInvocationHandler(employeeService));
        employeeServiceProxy.createEmployee();

    }
}
