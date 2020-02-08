package com.zakary.qingblog;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class QingblogApplicationTests {

    @Autowired
    private LoginService loginService;

    private Logger logger;
    @Test
    public void contextLoads() {
    }

    @Test
    public void loginTest1(){
        User user=new User();
        user.setUserMail("xxx@gmail.com");
        user.setUserPassword("12345678");
        loginService.login(user);
    }
    @Test
    public  void register(){
        User user=new User();
        user.setUserName("test1");
        user.setUserPassword("12345678");
        user.setUserState(0);
        user.setUserMail("xxx1@gmail.com");
        user.setUserTel("12345678");
        loginService.register(user);
    }

}
