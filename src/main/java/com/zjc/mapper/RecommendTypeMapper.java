package com.zjc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.pojo.RecommendType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendTypeMapper extends BaseMapper<RecommendType> {
    void cancelRecommend();

    void nowRecommend(int parseInt);

    List<RecommendType> findRecommendList();
}
