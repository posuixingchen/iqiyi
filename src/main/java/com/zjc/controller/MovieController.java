package com.zjc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.service.CategoryService;
import com.zjc.service.MovieService;
import com.zjc.utils.Code;
import com.zjc.utils.FileLoad;
import com.zjc.utils.R;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("movie")
public class MovieController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Value("${movieimages-save-path}")
    private String ImagesSavePath;

    @Autowired
    private MovieService movieService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/findMovie")
    public R findMovie() {
        List<Movie> result = movieService.findMovie();
        if (result != null) {
            return new R(Code.WORK_OK, "查询成功", result);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @PostMapping("/saveMovie")
    public R save(@RequestPart("file") MultipartFile file, HttpServletRequest request, Movie movie) {
        try {
            if (file != null) {
                String base64Str = Base64.encodeBase64String(file.getBytes());
                movie.setPic(base64Str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int flag = movieService.saveMovie(movie);
        FileLoad.upload(file, request);
        if (flag < 1) {
            return new R(Code.WORK_ERR, "保存失败");
        }
        return new R(Code.WORK_OK, "保存成功");
    }

    @GetMapping("/findMovieOne/{id}")
    public R findMovieOne(@PathVariable("id") int movieId) {
        Movie movie = movieService.findById(movieId);
        List<Category> allCategory = categoryService.findAll();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movie", movie);
        dataMap.put("allCategory", allCategory);
        if (dataMap != null) {
            return new R(Code.WORK_OK, "查询成功", dataMap);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @PostMapping("/updateMovie")
    public R updateMovie(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestBody Movie movie) {
        try {
            if (file != null) {
                String base64Str = Base64.encodeBase64String(file.getBytes());
                movie.setPic(base64Str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileLoad.upload(file, request);
        int flag = movieService.updateMovie(movie);
        if (flag < 1) {
            return new R(Code.WORK_ERR, "修改失败");
        }
        return new R(Code.WORK_OK, "修改成功");
    }

    @PostMapping("/deleteMovie/{id}")
    public R deleteMovie(@PathVariable("id") int movieId) {
        int flag = movieService.deleteMovie(movieId);
        if (flag != 1) {
            return new R(Code.WORK_ERR, "删除失败");
        }
        return new R(Code.WORK_OK, "删除成功");
    }

}
