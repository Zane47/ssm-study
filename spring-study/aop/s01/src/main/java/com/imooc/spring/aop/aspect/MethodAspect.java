package com.imooc.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

// 切面类
public class MethodAspect {
    // 切面方法,用于扩展额外功能
    // JoinPoint 连接点,通过连接点可以获取目标类/方法的信息
    public void printExecTime(JoinPoint joinPoint) {
        // ------------------------ 哪些类的哪些方法在什么时间执行 ------------------------
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String now = simpleDateFormat.format(new Date());
        // 获取目标类的名称
        String className = joinPoint.getTarget().getClass().getName();
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();

        System.out.println("---->" + now + ":" + className + "." + methodName);

        // 获取参数列表
        Object[] args = joinPoint.getArgs();
        System.out.println("---->参数个数: " + args.length);
        for (Object arg : args) {
            System.out.println("---->参数: " + arg);
        }
    }

    /**
     * 后置通知的处理方法
     */
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("<----触发后置通知");
    }

    /**
     *
     * @param joinPoint
     * @param ret 目标方法对象的返回值
     */
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        System.out.println("<----返回后通知" + ret);
    }

    /**
     *
     * @param joinPoint
     * @param th
     */
    public void doAfterThrowing(JoinPoint joinPoint, Throwable th) {
        System.out.println("<----异常通知" + th.getMessage());
    }


}
