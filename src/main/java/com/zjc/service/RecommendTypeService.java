package com.zjc.service;

import com.zjc.pojo.RecommendType;

import java.util.List;

public interface RecommendTypeService {
    List<RecommendType> findAll();

    void nowRecommend(String ids);
}
