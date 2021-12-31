package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
    public String getMapping(@RequestParam("manager_name") String managerName, Date createTime) {
        System.out.println("managerName: " + managerName);
        System.out.println("createTime: " + createTime.toString());
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
    public String postMapping1(User user, String username,
                               @DateTimeFormat(pattern = "yyyyMMdd") Date createtime) {
        // 不管有多少个参数, 只要参数名称和请求参数同名, 就全部都会赋值
        System.out.println(user.getUsername() + ": " + user.getPassword());
        System.out.println(createtime.toString());
        // return "这是post响应";
        return "<h1>hello<h1>";
    }

    @GetMapping("/view")
    public ModelAndView showView(Integer userId) {
        // 文件名中的 / 说明要存储在webapp的根路径下
        // ModelAndView modelAndView = new ModelAndView("/view.jsp");
        // ModelAndView modelAndView = new ModelAndView("redirect:/view.jsp");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/view.jsp");
        // modelAndView.setViewName("view.jsp");

        User user = new User();
        if (userId == 1) {
            user.setUsername("1");
        } else if (userId == 2) {
            user.setUsername("2");
        } else if (userId == 3) {
            user.setUsername("3");
        }

        // 在当前请求中增加对象, 别名u
        modelAndView.addObject("u", user);

        return modelAndView;
    }


    /**
     * String 和 ModelMap
     *
     * String与ModelMap
     * Controller方法返回string的情况
     * 1.方法被@ResponseBody描述，SpringMVc直接响应String字符串本身
     * 2.方法不存在@ResponseBody,则SpringMVc处理String指代的视图（页面）
     */
    @GetMapping("/xx")
    // @ResponseBody
    public String showView1(Integer userId, ModelMap modelMap) {
        String view = "/um/view.jsp";
        User user = new User();
        if (userId == 1) {
            user.setUsername("1");
        } else if (userId == 2) {
            user.setUsername("2");
        } else if (userId == 3) {
            user.setUsername("3");
        }

        modelMap.addAttribute("u", user);
        return view;
    }


}
