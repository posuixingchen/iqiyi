package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.User;
import com.zjc.mapper.UserMapper;
import com.zjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int registerUser(User user) {
        User entity = new User();
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        return userMapper.insert(entity);
    }

    @Override
    public User selectUser(QueryWrapper<User> wrapper) {
        return userMapper.selectOne(wrapper);
    }

}

