package com.zjc.service.impl;

import com.zjc.mapper.ActorMapper;
import com.zjc.mapper.ActorRegionMapper;
import com.zjc.pojo.Actor;
import com.zjc.pojo.ActorRegion;
import com.zjc.pojo.MovieCategory;
import com.zjc.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        int flag = actorMapper.saveActor(actor);
        int aid = actor.getId();
        String regionStr = actor.getRegionStr();
        String[] idArray = regionStr.split(",");
        for (String regionid : idArray) {
            int rid = Integer.parseInt(regionid);
            ActorRegion entity = new ActorRegion();
            entity.setActorid(aid);
            entity.setRegionid(rid);
            actorRegionMapper.insert(entity);
        }
        return flag;
    }

    @Override
    public Actor findById(int actorId) {
        return actorMapper.findActorOne(actorId);
    }

    @Override
    public void updateActor(Actor actor) {
        actorMapper.updateActor(actor);
        int aid = actor.getId();
        actorMapper.deleteActorRegion(aid);
        String regionStr = actor.getRegionStr();
        String[] idArray = regionStr.split(",");
        for (String regionid : idArray) {
            int rid = Integer.parseInt(regionid);
            ActorRegion entity = new ActorRegion();
            entity.setActorid(aid);
            entity.setRegionid(rid);
            actorRegionMapper.insert(entity);
        }
    }

    @Override
    public int deleteActor(int actorId) {
        actorMapper.deleteActorRegion(actorId);
        int flag = actorMapper.deleteActor(actorId);
        if (flag == 0) {
            return 0;
        } else return 1;
    }
}
