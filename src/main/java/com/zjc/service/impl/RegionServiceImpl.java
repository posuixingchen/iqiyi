package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.RegionMapper;
import com.zjc.pojo.Region;
import com.zjc.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> findAllRegion() {
        QueryWrapper<Region> wrapper = new QueryWrapper<>();
        return regionMapper.selectList(wrapper);
    }
}
