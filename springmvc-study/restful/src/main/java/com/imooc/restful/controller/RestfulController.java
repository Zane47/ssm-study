package com.imooc.restful.controller;

import com.imooc.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restful")
public class RestfulController {

    @GetMapping("/request")
    // @ResponseBody
    public String doGetRequest() {

        //使用\原义输出
        return "{\"message\":\"返回查询结果\"}";
    }

    // POST /article/1
    // POST /restful/request/100
    @PostMapping("/request/{rid}")
    // @ResponseBody
    public String doPostRequest(@PathVariable("rid") Integer requestId, Person person) {
        System.out.println(person.getName() + ": " + person.getAge());
        return "{\"message\":\"数据新建成功\", \"id\": " + requestId + "}";
    }

    @PutMapping("/request")
    // @ResponseBody
    public String doPutRequest(Person person) {
        System.out.println(person.getName() + ": " + person.getAge());
        return "{\"message\":\"数据更新成功\"}";
    }

    @DeleteMapping("/request")
    // @ResponseBody
    public String doDeleteRequest() {
        return "{\"message\":\"数据删除成功\"}";
    }


    // ------------------------ jackson ------------------------
    @GetMapping("/person")
    public Person getPersonById(Integer id) {
        Person person = new Person();
        if (id == 1) {
            person.setName("1");
            person.setAge(11);
        } else if (id == 2) {
            person.setName("2");
            person.setAge(22);
        } else  {
            person.setName("3");
            person.setAge(33);
        }
        return person;
    }



}
