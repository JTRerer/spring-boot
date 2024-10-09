package com.yuu.springboot.demosecurity.dao;

import com.yuu.springboot.demosecurity.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String theRoleName);
}
