package com.zakary.qingblog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${mysql.dbUrl}")
        private String dbUrl;
    @RequestMapping("/hello")
    public String hello(){
        return dbUrl;
    }

}
