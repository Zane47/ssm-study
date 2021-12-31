package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm")
public class FreemarkerController {

    @GetMapping("/test")
    public ModelAndView showTest() {
        // 因为配置了扩展名，所以不需要写完整名
        // /:代表根目录, 是配置了的/WEB-INF/ftl目录
        ModelAndView modelAndView = new ModelAndView("/test");

        User user = new User();
        user.setUsername("wahaha");
        modelAndView.addObject("u", user);
        return modelAndView;
    }
}
