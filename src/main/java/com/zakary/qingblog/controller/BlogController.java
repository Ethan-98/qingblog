package com.zakary.qingblog.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zakary.qingblog.domain.Blog;
import com.zakary.qingblog.domain.Page;
import com.zakary.qingblog.service.BlogService;
import com.zakary.qingblog.utils.JSONResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private FileController fileController;

    @RequestMapping(value = "/upLoadMarkdown",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult upLoadMarkdown(HttpServletRequest request, @RequestBody Blog blog){
        String id = request.getSession().getAttribute("userId").toString();
        int userId=Integer.parseInt(id);
        blog.setUserId(userId);
        blogService.addBlog(blog);
        //通过mapper中useGeneratedKeys="true" keyProperty="blogId"来设定返回blogid在javabean中，通过get（）得到
        return JSONResult.ok(blog.getBlogId());
    }

    @RequestMapping(value = "/delBlog",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delBlog(@RequestBody Blog blog, @Param(value = "Imgs") String imgs[]){
        blogService.deleteBlog(blog.getBlogId());
        for(String img:imgs){
            fileController.delImg(img);
        }
        return JSONResult.ok();
    }

    @RequestMapping(value = "/alterBlog",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult alterBlog(HttpServletRequest request,@RequestBody Blog blog){
        blogService.updateBlog(blog);
        //通过mapper中useGeneratedKeys="true" keyProperty="blogId"来设定返回blogid在javabean中，通过get（）得到
        return JSONResult.ok(blog.getBlogId());
    }

    @RequestMapping(value = "/viewBlog",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult queryBlogInfo(@RequestBody Blog blog){
        int blogId=blog.getBlogId();
        return JSONResult.ok(blogService.queryBlogInfo(blogId));

//        return JSONResult.ok(blogService.queryBlogInfo(Integer.parseInt(blogId)));
    }
    /**
     *@description:
     *@param:  * @param null
     *@return: title blogId views date
     *@Author: Zakary
     *@date: 2020/2/14 14:32
    */
    @RequestMapping(value = "/viewBlogList",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult viewBlogList(HttpServletRequest request){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        List<Blog> blogs=blogService.queryBlogListIntro(userId);
        return JSONResult.ok(blogs);
    }

    @RequestMapping(value = "/viewAllBlogList",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult viewSelfAllBlogList(HttpServletRequest request,@RequestBody Page page){
        System.out.println(page.toString());
        int start=(page.getPageNo()-1)*page.getPageSize();
        int end=(page.getPageNo()-1)*page.getPageSize()+page.getPageSize();
        page.setPageNo(start);
        page.setPageSize(end);
        List<Blog> blogs=blogService.queryAllBlogListIntro(page);
        System.out.println(blogs.toString());
        return JSONResult.ok(blogs);
    }

}
