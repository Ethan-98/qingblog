package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.service.BlogService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/upLoadMarkdown",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult upLoadMarkdown(HttpServletRequest request, @RequestBody Blog blog){
        String id = request.getSession().getAttribute("userId").toString();
        int userId=Integer.parseInt(id);
        blog.setUserId(userId);
        blogService.addBlog(blog);
        return JSONResult.ok();
    }
}
