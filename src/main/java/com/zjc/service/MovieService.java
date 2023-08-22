package com.zjc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.Movie;

import java.util.List;

public interface MovieService {
    //新增一条电影记录
    int addMovie(Movie movie);

    //删除一条电影记录
    int deleteMovie(Integer id);

    //更新一条电影记录
    int updateMovie(Movie movie);

    //查询所有电影
    List<Movie> findMovie();
}
