package com.zjc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@TableName("movie")
public class Movie {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String name;

    @TableField("director")
    private String director;

    @TableField("time_length")
    private int time_length;

    @TableField("pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;

    @TableField("description")
    private String description;

    @TableField("rating")
    private float rating;

    @TableField("pic")
    private String pic;

    @TableField("url")
    private String url;

    private List<Actor> actors;

    private String actorIds;

    private String[] actorStd;

    private List<Category> categories;

    private String categoryStr;

    public Movie() {
    }
}
