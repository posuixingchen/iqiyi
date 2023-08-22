package com.zjc.controller;

import com.zjc.pojo.Actor;
import com.zjc.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/findActor")
    public List<Actor> findActor() {
        return actorService.findActor();
    }
}
