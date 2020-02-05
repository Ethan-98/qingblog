package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
import com.zakary.qingblog.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login(){
        return "userLogin";
    }
    /**
     *@description: 登录功能
     *@param:  * @param null
     *@return: 
     *@Author: Zakary
     *@date: 2020/2/4 22:10
    */
    @RequestMapping("/userLogin")
    @ResponseBody
    public JSONResult userLogin(@RequestBody User user, HttpServletRequest request){
        User user1=loginService.login(user);
        HttpSession session=request.getSession();
        session.setAttribute("userId",user1.getUserId());
        return new JSONResult();
    }


}
