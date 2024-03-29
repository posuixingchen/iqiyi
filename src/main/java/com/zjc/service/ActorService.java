package com.zjc.service;

import com.zjc.pojo.Actor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActorService {
    List<Actor> findActor();

    int saveActor(Actor actor);

    Actor findById(int actorId);

    void updateActor(Actor actor);

    int deleteActor(int actorId);
}
