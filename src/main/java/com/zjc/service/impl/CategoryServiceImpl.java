package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.CategoryMapper;
import com.zjc.pojo.Category;
import com.zjc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll(QueryWrapper<Category> wrapper) {
        return categoryMapper.selectList(wrapper);
    }
}
