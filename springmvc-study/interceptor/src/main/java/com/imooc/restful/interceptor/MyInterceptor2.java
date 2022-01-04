package com.imooc.restful.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器必须实现HandlerInterceptor接口, 同时必须实现以下三个方法:
 * <p>
 * * preHandler: 前置执行处理. 请求产生后,
 * 还没有进入controller前, 先进preHandler, 对请求进行预置处理.
 * <p>
 * * postHandle: 目标资源已被SpringMVC框架处理. 例子:
 * 如果是在controller, 就是在内部方法return之后，但是还没有产生响应文本之前, 执行posthandler.
 * <p>
 * * afterCompletion: 响应文本已经产生了, 执行afterCompletion.
 * 比如在jackson自动实现序列化之后.
 * 例如: 返回modelandview, 数据和模板引擎混合, 产生html片段, afterCompletion就会执行.
 */
public class MyInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle2: " + request.getRequestURL() + "-准备执行2");
        return true;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler2: " + request.getRequestURI() + "-处理成功2");

        // HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion2: " + request.getRequestURI() + "-响应内容已产生2");
        // HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
