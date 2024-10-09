package com.yuu.springboot.demosecurity.dao;

import com.yuu.springboot.demosecurity.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
