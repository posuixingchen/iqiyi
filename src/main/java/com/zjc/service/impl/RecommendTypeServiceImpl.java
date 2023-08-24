package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.RecommendTypeMapper;
import com.zjc.pojo.RecommendType;
import com.zjc.service.RecommendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendTypeServiceImpl implements RecommendTypeService {
    @Autowired
    private RecommendTypeMapper recommendTypeMapper;

    @Override
    public List<RecommendType> findAll() {
        QueryWrapper<RecommendType> wrapper = new QueryWrapper<>();
        return recommendTypeMapper.selectList(wrapper);
    }

    @Override
    public void nowRecommend(String ids) {
        recommendTypeMapper.cancelRecommend();
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            recommendTypeMapper.nowRecommend(Integer.parseInt(id));
        }
    }
}
