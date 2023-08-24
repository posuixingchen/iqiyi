package com.zjc.service.impl;

import com.zjc.mapper.ActorMapper;
import com.zjc.mapper.ActorRegionMapper;
import com.zjc.pojo.Actor;
import com.zjc.pojo.ActorRegion;
import com.zjc.pojo.MovieCategory;
import com.zjc.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;
    @Autowired
    private ActorRegionMapper actorRegionMapper;

    @Override
    public List<Actor> findActor() {
        return actorMapper.findActor();
    }

    @Override
    public int saveActor(Actor actor) {
        int flag = actorMapper.saveActor();
        String regionStr = actor.getRegionStr();
        String[] idArray = regionStr.split(",");
        for (String regionid : idArray) {
            int rid = Integer.parseInt(regionid);
            ActorRegion entity = new ActorRegion();
            entity.setActorid(actor.getId());
            entity.setRegionid(rid);
            actorRegionMapper.insert(entity);
        }
        return flag;
    }
}
