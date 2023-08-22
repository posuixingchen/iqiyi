package com.zjc.service.impl;

import com.zjc.mapper.ActorMapper;
import com.zjc.pojo.Actor;
import com.zjc.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<Actor> findActor() {
        return actorMapper.findActor();
    }
}
