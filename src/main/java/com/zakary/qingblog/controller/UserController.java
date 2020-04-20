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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private LoginService loginService;

    Logger logger;

    @RequestMapping("/login")
    public String login(){
        return "userLogin";
    }

    @RequestMapping("/test")
    public String file(){ return "test"; }


    @RequestMapping("/register")
    public String register(){ return "register"; }

    //返回信息包括分配的次用户ID
    @RequestMapping("/userRegister")
    @ResponseBody
    public JSONResult userRegister(@RequestBody @Validated({ValidationGroups.ResignGroup.class}) User user){
        User user1=loginService.register(user);
        return JSONResult.ok(user1.getUserId());
    }
    /**
     *@description: 用户更新个人信息，必须是登陆状态，不能修改密码
     *@param:  * @param userId,userName,userMail,userTel
     *@return: 用户全部信息
     *@Author: Zakary
     *@date: 2020/2/9 18:51
    */
    @RequestMapping("/userUpdateInfo")
    @ResponseBody
    public JSONResult userUpdateInfo(HttpServletRequest request,@RequestBody User user){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
        user.setUserId(userId);
        return JSONResult.ok(loginService.updateInfo(user));
    }

    /**
     *@description: 用户更新密码
     *@param:  * @param userId，userPassword
     *@return:
     *@Author: Zakary
     *@date: 2020/2/9 18:51
     */
    @RequestMapping("/userUpdatePwd")
    @ResponseBody
    public JSONResult userUpdatePwd(HttpServletRequest request,@RequestBody User user){
        user.setUserId(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        return JSONResult.ok(loginService.updatePwd(user));
    }
    /**
     *@description: 查询用户自己的所有信息
     *@param:  * @param UserId
     *@return:
     *@Author: Zakary
     *@date: 2020/2/9 19:19
    */
    @RequestMapping("/selectInfo")
    @ResponseBody
    public JSONResult selectInfo(HttpServletRequest request){
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
//        System.out.println("controller: selectInfo"+userId);
        return JSONResult.ok(loginService.selectExceptPwd(userId));
    }

    /**
     *@description: 根据userId查询用户所有信息
     *@param:  * @param UserId
     *@return:
     *@Author: Zakary
     *@date: 2020/2/9 19:19
     */
    @RequestMapping("/selectInfoByUserId")
    @ResponseBody
    public JSONResult selectInfoByUserId(@RequestBody User user){
//        System.out.println("controller: selectInfoByUserId"+user.getUserId());
        User user1=loginService.selectExceptPwd(user.getUserId());
//        System.out.println(user1);
        return JSONResult.ok(user1);
    }
}
