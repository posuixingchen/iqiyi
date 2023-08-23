package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.MovieActorMapper;
import com.zjc.pojo.MovieActor;
import com.zjc.service.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieActorServiceImpl implements MovieActorService {

    @Autowired
    private MovieActorMapper movieActorMapper;
}
