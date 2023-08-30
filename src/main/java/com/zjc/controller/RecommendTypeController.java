package com.zjc.controller;

import com.zjc.pojo.RecommendType;
import com.zjc.service.RecommendTypeService;
import com.zjc.utils.Code;
import com.zjc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("recommendType")
public class RecommendTypeController {
    @Autowired
    private RecommendTypeService recommendTypeService;

    @GetMapping("/findAll")
    public R findAll() {
        List<RecommendType> result = recommendTypeService.findAll();
        if (result != null) {
            return new R(Code.WORK_OK, "查询成功");
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @GetMapping("/nowRecommend")
    public R nowRecommend(String ids) {
        recommendTypeService.nowRecommend(ids);
        return new R(Code.WORK_OK, "获取成功");
    }
}
