package com.zakary.qingblog.service;

import com.zakary.qingblog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface LoginService {
    public User login(User user);
}
