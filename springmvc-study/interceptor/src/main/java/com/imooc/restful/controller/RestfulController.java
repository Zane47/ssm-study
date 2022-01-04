package com.imooc.restful.controller;

import com.imooc.restful.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("restful")
//@CrossOrigin(origins = {"http://localhost:8082", "http://www.imooc.com"})
//@CrossOrigin(origins = "*", maxAge = 3600)
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


    // ------------------------ json序列化: jackson ------------------------
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

    // 多条数据json
    @GetMapping("/persons")
    public List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.get(0).setName("zero");
        personList.get(0).setAge(10);
        personList.get(0).setBirthday(new Date());
        personList.add(new Person());
        personList.get(1).setName("one");
        personList.get(1).setAge(11);
        personList.get(1).setBirthday(new Date());
        personList.add(new Person());
        personList.get(2).setName("two");
        personList.get(2).setAge(12);
        personList.get(2).setBirthday(new Date());
        System.out.println("RestfulController getPersons");
        return personList;
    }



}
