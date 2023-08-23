package com.zjc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll(QueryWrapper<Category> wrapper);
}
