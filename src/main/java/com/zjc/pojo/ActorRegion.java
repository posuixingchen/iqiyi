package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("actor_region")
public class ActorRegion {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("actorid")
    private int actorid;

    @TableField("regionid")
    private int regionid;

}
