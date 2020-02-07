package com.zakary.qingblog.service.serviceImpl;

import com.zakary.qingblog.domain.User;
import com.zakary.qingblog.exp.BusinessException;
import com.zakary.qingblog.mapper.UserMapper;
import com.zakary.qingblog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ClassNameloginServiceImpl
 * @Description
 * @Author
 * @Date2020/2/4 21:59
 * @Version V1.0
 **/
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        int count=userMapper.selectCountByUserMail(user.getUserMail());
        if(count==0){
            throw new BusinessException("该用户不存在！");
        }
        User user1= userMapper.selectByUserMail(user.getUserMail());
        if(!user.getUserPassword().equals(user1.getUserPassword())){
            throw new BusinessException("密码错误");
        }
        return user1;
    }

    @Override
    public User register(User user){
        int count=userMapper.selectCountByUserMail(user.getUserMail());
        if(count!=0){
            throw new BusinessException("该用户已存在！");
        }
        //1为test
        user.setUserId(userMapper.selectAllCount()+10001);
        user.setUserRegisterDate(new Date());
        user.setUserState(0);
        userMapper.insert(user);
        return userMapper.selectByUserMail(user.getUserMail());
    }
}
