package com.zjc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.pojo.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegionMapper extends BaseMapper<Region> {
}
