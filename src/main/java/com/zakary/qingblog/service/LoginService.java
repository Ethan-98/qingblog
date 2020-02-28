package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface LoginService {
    public User login(User user);
    public User register(User user);
    public User updateInfo(User user);
    public User updatePwd(User user);
    public User selectExceptPwd(int userId);
    public void setProfile(int userId,String id);
}
