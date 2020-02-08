package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
import com.zakary.qingblog.utils.JSONResult;
import com.zakary.qingblog.validation.ValidationGroups;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    Logger logger;

    @RequestMapping("/login")
    public String login(){
        return "userLogin";
    }

    @RequestMapping("/homePage")
    public String homePage(){ return "homePage"; }

    @RequestMapping("/register")
    public String register(){ return "register"; }
    /**
     *@description: 登录功能
     *@param:  * @param null
     *@return: 
     *@Author: Zakary
     *@date: 2020/2/4 22:10
    */
    @RequestMapping("/userLogin")
    @ResponseBody
    public JSONResult userLogin(@RequestBody @Validated({ValidationGroups.LoginGroup.class}) User user, HttpServletRequest request){
//        logger.info("user: "+user.getUserMail());
        User user1=loginService.login(user);
        HttpSession session=request.getSession();
        session.setAttribute("userId",user1.getUserId());
        return JSONResult.ok("success");
    }

    //返回信息包括分配的次用户ID
    @RequestMapping("/userRegister")
    @ResponseBody
    public JSONResult userRegister(@RequestBody @Validated({ValidationGroups.ResignGroup.class}) User user){
        User user1=loginService.register(user);
        return JSONResult.ok(user1.getUserId());
    }
}
