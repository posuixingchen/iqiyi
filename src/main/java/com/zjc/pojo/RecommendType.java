package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recommend_type")
public class RecommendType {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("name")
    private int name;

    @TableField("isrecommend")
    private int isrecommend;
}
