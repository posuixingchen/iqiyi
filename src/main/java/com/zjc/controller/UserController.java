package com.zjc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.User;
import com.zjc.service.UserService;
import com.zjc.utils.Code;
import com.zjc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("name", user.getName());

        User result = userService.selectUser(wrapper);
        if (result != null) {
            return new R(Code.WORK_ERR, "用户名已存在");
        } else {
            int flag = userService.registerUser(user);
            if (flag != 1) {
                return new R(Code.WORK_ERR, "注册失败");
            }
            return new R(Code.WORK_OK, "注册成功");
        }
    }

    @PostMapping("/login/{name}/{password}")
    public R login(@PathVariable("name") String name, @PathVariable("password") String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("name", name);

        User result = userService.selectUser(wrapper);
        if (!result.getPassword().equals(password)) {
            return new R(Code.WORK_ERR, "登录失败");
        }
        return new R(Code.WORK_OK, "登录成功", result);
    }
}
