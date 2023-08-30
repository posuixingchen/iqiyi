package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_status")
public class UserStatus {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("weekday")
    private String weekday;

    @TableField("newin")
    private int newin;

    @TableField("active")
    private int active;
}
