package com.imooc.springmvc.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class User {
    private String username;
    private Long password;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date createtime;
}
