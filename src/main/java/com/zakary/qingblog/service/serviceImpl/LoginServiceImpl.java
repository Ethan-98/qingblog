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
import java.util.regex.Pattern;

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

    final String reMail = "/([a-z]|\\d)+\\@([a-z]|\\d)+\\.([a-z]|\\d)+/i";

    @Override
    public User login(User user) {
        int count = userMapper.selectCountByUserMail(user.getUserMail());
        if (count == 0) {
            throw new BusinessException("该用户不存在！");
        }
        User user1 = userMapper.selectByUserMail(user.getUserMail());
        if (!user.getUserPassword().equals(user1.getUserPassword())) {
            throw new BusinessException("密码错误");
        }
        return user1;
    }

    @Override
    public User register(User user) {
        System.out.println(user.getUserMail());
        int count = userMapper.selectCountByUserMail(user.getUserMail());
        if (count != 0) {
            throw new BusinessException("该用户已存在！");
        }
        //1为test
//        user.setUserId(userMapper.selectAllCount() + 10001);
        user.setUserRegisterDate(new Date());
        user.setUserState(0);
        userMapper.insert(user);
        return userMapper.selectByUserMail(user.getUserMail());
    }

    @Override
    public User updateInfo(User user){
        User user1=userMapper.selectByPrimaryKey(user.getUserId());
        if(userMapper.selectCountByUserMail(user.getUserMail())>0&&!user1.getUserMail().equals(user.getUserMail())){
            throw new BusinessException("此邮箱已注册！");
        }
        user.setUserPassword(user1.getUserPassword());
        user.setUserRegisterDate(user1.getUserRegisterDate());
        user.setUserImage(user1.getUserImage());
        user.setUserState(user1.getUserState());
        userMapper.updateByPrimaryKey(user);
        return selectExceptPwd(user.getUserId());
    }

    @Override
    public User updatePwd(User user){
        System.out.println("_____________________________________________");
        System.out.println(user.toString());
        User user1=userMapper.selectByPrimaryKey(user.getUserId());
        user1.setUserPassword(user.getUserPassword());
        userMapper.updateByPrimaryKey(user1);
        return selectExceptPwd(user.getUserId());
    }

    @Override
    public User selectExceptPwd(int userId){
        User user1=userMapper.selectByPrimaryKey(userId);
        user1.setUserPassword(null);
        return user1;
    }

    @Override
    public void setProfile(int userId,String id){
        userMapper.updateProfile(userId,id);
//        return user;
    }

    @Override
    public User getUserInfo(String userMail) {
        return userMapper.selectInfo(userMail);
    }
}
