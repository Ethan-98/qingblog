package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.BlogPraise;
import com.zakary.qingblog.service.BlogPraiseService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassNameBlogPraiseController
 * @Description
 * @Author
 * @Date2020/3/10 16:52
 * @Version V1.0
 **/
@Controller
public class BlogPraiseController {
    @Autowired
    private BlogPraiseService blogPraiseService;
    /**
     *@description: 用户点赞文章
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/10 20:31
    */
    @RequestMapping(value = "/addBlogPraise",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addBlogPraise(HttpServletRequest request, @RequestBody BlogPraise blogPraise){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        blogPraise.setUserId(userId);
        blogPraiseService.insertBlogPraise(blogPraise);
        return JSONResult.ok();
    }

    /**
     *@description: 用户取消点赞
     *@param:  * @param null
     *@return:
     *@Author: Zakary
     *@date: 2020/3/10 20:31
    */
    @RequestMapping(value = "/delBlogPraise",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delBlogPraise(HttpServletRequest request, @RequestBody BlogPraise blogPraise){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        blogPraise.setUserId(userId);
        blogPraiseService.delBlogPraise(blogPraise);
        return JSONResult.ok();
    }

    /**
     *@description: 查询用户是否已点赞此文章
     *@param:  * @param null
     *@return: int值，大于0则已经点赞
     *@Author: Zakary
     *@date: 2020/3/10 20:37
    */
    @RequestMapping(value = "/selectBlogPraise",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult selectBlogPraise(HttpServletRequest request, @RequestBody BlogPraise blogPraise){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        blogPraise.setUserId(userId);
        int count=blogPraiseService.selectBlogPraise(blogPraise);
        return JSONResult.ok(count);
    }
}
