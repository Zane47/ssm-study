package com.imooc.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("restful")
public class RestfulController {

    @GetMapping("/request")
    @ResponseBody
    public String doGetRequest() {

        //使用\原义输出
        return "{\"message\":\"返回查询结果\"}";
    }


    @PostMapping("/request")
    @ResponseBody
    public String doPostRequest() {
        return "{\"message\":\"数据新建成功\"}";
    }

    @PutMapping("/request")
    @ResponseBody
    public String doPutRequest() {
        return "{\"message\":\"数据更新成功\"}";
    }

    @DeleteMapping("/request")
    @ResponseBody
    public String doDeleteRequest() {
        return "{\"message\":\"数据删除成功\"}";
    }

}
