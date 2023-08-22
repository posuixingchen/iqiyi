package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableField("uid")
    private int uid;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;
}