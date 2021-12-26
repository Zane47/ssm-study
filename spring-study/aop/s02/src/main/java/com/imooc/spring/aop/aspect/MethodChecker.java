package com.imooc.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MethodChecker {
    /**
     * ProceedingJoinPoint是JoinPoint的升级版，在原有功能外，还可以控制目标方法是否执行
     * 环绕通知可以完成之前的四种通知的所有工作
     *
     * 注意这里方法的返回值是Object, 将目标方法的返回值进行返回
     */
    public Object check(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Long startTime = new Date().getTime();

            // 执行目标方法, 返回值为目标方法的返回值
            Object ret = proceedingJoinPoint.proceed();


            Long endTime = new Date().getTime();

            long duration = endTime - startTime;
            if (duration >= 1000) {
                String className = proceedingJoinPoint.getTarget().getClass().getName();
                String methodName = proceedingJoinPoint.getSignature().getName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String now = sdf.format(new Date());
                System.out.println("=====" + now + ":" + className + "." + methodName + "(" + duration +")");
            }
            return ret;
        } catch (Throwable throwable) {
            System.out.println("Exception Message: " + throwable.getMessage());
            throw throwable;
        }

    }
}
