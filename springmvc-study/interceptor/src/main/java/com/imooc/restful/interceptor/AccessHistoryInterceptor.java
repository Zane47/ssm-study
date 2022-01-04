package com.imooc.restful.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessHistoryInterceptor implements HandlerInterceptor {

    // 创建一个logger日志对象
    private final Logger logger = LoggerFactory.getLogger(AccessHistoryInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder log = new StringBuilder();
        log.append(request.getRemoteAddr());
        log.append("|");
        log.append(request.getRequestURL());
        log.append("|");
        // 用户的客户端环境保存在user-agent请求头中
        log.append(request.getHeader("user-agent"));

        logger.info(log.toString());
        return true;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }


}
