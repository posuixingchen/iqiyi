package com.zjc.controller;

import com.zjc.pojo.Actor;
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
import java.util.List;
import java.util.UUID;

@RestController
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

}
