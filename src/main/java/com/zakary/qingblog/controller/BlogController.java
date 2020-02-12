package com.zakary.qingblog.controller;

import com.zakary.qingblog.mapper.BlogMapper;
import com.zakary.qingblog.service.BlogService;
import com.zakary.qingblog.utils.JSONResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;

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
    public JSONResult upLoadMarkdown(HttpServletRequest request, @RequestParam String title,@RequestParam String text){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        blogService.addBlog(userId,title,text);
        return JSONResult.ok();
    }
}
