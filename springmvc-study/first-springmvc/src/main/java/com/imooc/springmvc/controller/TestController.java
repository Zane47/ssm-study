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
        return "test: hello spring mvc";
    }
}
