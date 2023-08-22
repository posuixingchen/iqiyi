package com.zjc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.pojo.User;

import java.util.List;

public interface UserService {

    //保存注册用户
    int registerUser(User user);

    //查询用户
    User selectUser(QueryWrapper<User> wrapper);

}
