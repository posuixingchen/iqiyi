package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("actor")
public class Actor {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String name;

    @TableField("birthday")
    private Date birthday;

    @TableField("sex")
    private String sex;

    @TableField("description")
    private String description;

    @TableField("pic")
    private String pic;

    private List<Region> regions;

    public Actor() {
    }
}
