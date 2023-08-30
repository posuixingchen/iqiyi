package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.UserMapper;
import com.zjc.mapper.UserStatusMapper;
import com.zjc.pojo.UserStatus;
import com.zjc.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    @Autowired
    private UserStatusMapper userStatusMapper;

    @Override
    public List<UserStatus> findUserStatus() {
        QueryWrapper<UserStatus> wrapper = new QueryWrapper<>();
        return userStatusMapper.selectList(wrapper);
    }
}
