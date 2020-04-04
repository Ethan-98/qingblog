package com.zakary.qingblog.controller;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
import com.zakary.qingblog.utils.JSONResult;
import com.zakary.qingblog.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassNameLoginController
 * @Description
 * @Author
 * @Date2020/3/30 16:08
 * @Version V1.0
 **/
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
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
        User user1=loginService.login(user);
        HttpSession session=request.getSession();
        session.setAttribute("userId",user1.getUserId());
        return JSONResult.ok("success");
    }
}
