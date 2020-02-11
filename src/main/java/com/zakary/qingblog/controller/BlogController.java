package com.zakary.qingblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassNameBlogController
 * @Description
 * @Author
 * @Date2020/2/11 14:23
 * @Version V1.0
 **/
@Controller
public class BlogController {

    @RequestMapping("/blogEdit")
    public String blogEdit(){
        return "blogEdit";
    }
}
