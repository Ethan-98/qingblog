package com.zakary.qingblog.test;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @ClassNameloginTest
 * @Description
 * @Author
 * @Date2020/2/5 17:01
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class loginTest {

    @Autowired
    private LoginService loginService;

    private Logger logger;
    @Test
    public void contextLoads() {
    }

    @Test
    public void loginTest1(){
        User user=new User();
        user.setUserName("test");
        user.setUserPassword("12345678");
        loginService.login(user);
    }

}
