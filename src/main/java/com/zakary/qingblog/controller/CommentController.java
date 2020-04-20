package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.Comment;
import com.zakary.qingblog.service.CommentService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassNameCommentController
 * @Description
 * @Author
 * @Date2020/4/4 13:51
 * @Version V1.0
 **/
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult insert(@RequestBody Comment comment, HttpServletRequest request){
        HttpSession session=request.getSession();
        int userId=Integer.parseInt(session.getAttribute("userId").toString());
        comment.setCommentUserId(userId);
        commentService.insert(comment);
        return JSONResult.ok();
    }

    @RequestMapping(value = "/{blogId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getCommentList(@PathVariable("blogId") int blogId,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
        int start=(pageNo-1)*pageSize;
        int end=(pageNo-1)*pageSize+pageSize;
        return JSONResult.ok(commentService.selectAllByBlogId(blogId,start,end));
    }

    @RequestMapping(value = "/commentId/{commentId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getChildComment(@PathVariable("commentId") int commentId,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
        int start=(pageNo-1)*pageSize;
        int end=(pageNo-1)*pageSize+pageSize;
        return JSONResult.ok(commentService.selectChildCommentByParentId(commentId,start,end));
    }

    @RequestMapping(value = "/selectComment/{commentId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getCommentByCommentId(@PathVariable("commentId") int commentId){
        return JSONResult.ok(commentService.selectCommentByCommentId(commentId));
    }
}
