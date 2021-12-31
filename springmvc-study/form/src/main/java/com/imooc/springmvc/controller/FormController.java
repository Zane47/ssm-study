package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FormController {

    /**
     * 数组方法接收参数
     */
    /*@PostMapping("/apply")
    @ResponseBody
    public String apply(@RequestParam(value = "name", defaultValue = "anonymous") String duck, String course, Integer[] purpose) {
        System.out.println(duck);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }

        return "success";
    }*/

    /**
     * list方法接收参数
     *
     * 必须在方法参数前使用@RequestParam注解,
     * 这样springmvc才会知道请求中包含的复合数据转化为list形式进行存储
     *
     */
    /*@PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course,
                        @RequestParam List<Integer> purpose) {
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }

        return "success";
    }*/

    /**
     * 实体类
     */
    /*@PostMapping("/apply")
    @ResponseBody
    public String apply(Form form) {
        System.out.println(form.getName());
        System.out.println(form.getCourse());

        for (Integer p : form.getPurpose()) {
            System.out.println(p);
        }

        return "success";
    }
    */

    /*@PostMapping("/apply")
    @ResponseBody
    public String apply(@RequestParam Map map) {
        System.out.println(map);
        return "success";
    }*/

    @PostMapping("/apply")
    @ResponseBody
    public String applyDelivery(Form form) {
        System.out.println(form);
        return "success";
    }

}
