package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("movie_category")
public class MovieCategory {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("movieid")
    private int movieid;

    @TableField("categoryid")
    private int categoryid;
}
