package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.MovieCategoryMapper;
import com.zjc.pojo.MovieCategory;
import com.zjc.service.MovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCategoryServiceImpl implements MovieCategoryService {

    @Autowired
    private MovieCategoryMapper movieCategoryMapper;
}
