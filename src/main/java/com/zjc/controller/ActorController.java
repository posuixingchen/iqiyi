package com.zjc.controller;

import com.zjc.pojo.Actor;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.pojo.Region;
import com.zjc.service.ActorService;
import com.zjc.service.RegionService;
import com.zjc.utils.Code;
import com.zjc.utils.FileLoad;
import com.zjc.utils.R;
import org.apache.http.HttpRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("actor")
public class ActorController {

    @Value("${movieimages-save-path}")
    private String ImagesSavePath;

    @Autowired
    private ActorService actorService;
    @Autowired
    private RegionService regionService;

    @GetMapping("/findActor")
    public R findActor() {
        List<Actor> result = actorService.findActor();
        if (result != null) {
            return new R(Code.WORK_OK, "查询成功", result);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @GetMapping("/findAllRegion")
    public R findAllRegion() {
        List<Region> result = regionService.findAllRegion();
        if (result != null) {
            return new R(Code.WORK_OK, "查询成功", result);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @PostMapping("/saveActor")
    public R save(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, Actor actor) {
        try {
            if (file != null) {
                String base64Str = Base64.encodeBase64String(file.getBytes());
                actor.setPic(base64Str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int flag = actorService.saveActor(actor);
        FileLoad.upload(file, request);
        return new R(Code.WORK_OK, "保存成功");
    }

    @PostMapping("/findActorOne/{id}")
    public R findActorOne(@PathVariable("id") int actorId) {
        Actor actor = actorService.findById(actorId);
        List<Region> allRegion = regionService.findAllRegion();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("actor", actor);
        dataMap.put("allRegion", allRegion);
        if (dataMap != null) {
            return new R(Code.WORK_OK, "查询成功", dataMap);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }


    @PostMapping("/updateActor")
    public R updateActor(@RequestParam("file") MultipartFile file, HttpServletRequest request, Actor actor) {
        try {
            if (file != null) {
                String base64Str = Base64.encodeBase64String(file.getBytes());
                actor.setPic(base64Str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileLoad.upload(file, request);
        actorService.updateActor(actor);
        return new R(Code.WORK_OK, "更新成功");
    }

    @PostMapping("/deleteActor/{id}")
    public R deleteActor(@PathVariable("id") int actorId) {
        int flag = actorService.deleteActor(actorId);
        if (flag != 1) {
            return new R(Code.WORK_ERR, "删除失败");
        }
        return new R(Code.WORK_OK, "删除成功");
    }
}
