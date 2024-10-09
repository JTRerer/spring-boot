package com.yuu.springboot.demosecurity.service;

import com.yuu.springboot.demosecurity.entity.User;
import com.yuu.springboot.demosecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);

    void save(WebUser webUser);
}
