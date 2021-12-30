package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/um")
public class URLMappingController {

    // 不用区分get/post请求
    // @RequestMapping("/q")
    @RequestMapping(method = RequestMethod.GET, value = "/q")
    @ResponseBody
    public String requestMapping() {
        return "request mapping";
    }

    @GetMapping("/g")
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName) {
        System.out.println("managerName: " + managerName);
        return "this is get method. " + managerName;
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping() {
        return "this is post method";
    }


    @PostMapping("/m1")
    @ResponseBody
    public String getUserNamePwd(String username, Long password) {
        // servlet的参数获取, 需要使用: request.getParamter(). mvc的简单

        System.out.println(username + ":" + password);
        return username + ":" + password;
    }

    @PostMapping("p1")
    @ResponseBody
    public String postMapping1(User user, String username) {
        // 不管有多少个参数, 只要参数名称和请求参数同名, 就全部都会赋值
        System.out.println(user.getUsername() + ": " + user.getPassword());
        return "postMapping1";
    }

}
