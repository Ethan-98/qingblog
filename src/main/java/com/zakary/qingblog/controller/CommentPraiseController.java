package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.CommentPraise;
import com.zakary.qingblog.service.CommentPraiseService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassNameCommentPraiseController
 * @Description
 * @Author
 * @Date2020/4/5 22:12
 * @Version V1.0
 **/
@Controller
@RequestMapping("/commentPraise")
public class CommentPraiseController {

    @Autowired
    private CommentPraiseService commentPraiseService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult insert(@RequestBody CommentPraise commentPraise, HttpServletRequest request){
        HttpSession session=request.getSession();
        int userId=Integer.parseInt(session.getAttribute("userId").toString());
        commentPraise.setUserId(userId);
        commentPraiseService.insert(commentPraise);
        return JSONResult.ok();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult delete(@RequestBody CommentPraise commentPraise,HttpServletRequest request){
        HttpSession session=request.getSession();
        int userId=Integer.parseInt(session.getAttribute("userId").toString());
        commentPraise.setUserId(userId);
        commentPraiseService.delete(commentPraise);
        return JSONResult.ok();
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult select(@RequestBody CommentPraise commentPraise,HttpServletRequest request){
        HttpSession session=request.getSession();
        int userId=Integer.parseInt(session.getAttribute("userId").toString());
        commentPraise.setUserId(userId);
//        System.out.println(commentPraise.toString());
        return JSONResult.ok(commentPraiseService.select(commentPraise));
    }
}
