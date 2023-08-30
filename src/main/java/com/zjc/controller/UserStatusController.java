package com.zjc.controller;

import com.zjc.pojo.UserStatus;
import com.zjc.service.UserStatusService;
import com.zjc.utils.Code;
import com.zjc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("home")
public class UserStatusController {
    @Autowired
    private UserStatusService userStatusService;

    @GetMapping("/getUserStatus")
    public R getUserStatus() {
        List<UserStatus> result = userStatusService.findUserStatus();
        if (result != null) {
            return new R(Code.WORK_OK, "查询成功", result);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }
}
