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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("movie")
public class MovieController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

//    @PostMapping("/upLoad")
//    public R upLoad(@RequestParam("file") MultipartFile file) {
//        String base64 = FileLoad.upload(file);
//        if (base64 != null) {
//            return new R(Code.WORK_ERR, "上传失败");
//        }
//        return new R(Code.WORK_OK, "上传成功");
//    }

    @PostMapping("/saveMovie")
    @ResponseBody
    public R save(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        Movie movie = new Movie();
        movie.setName(params.getParameter("name"));
        movie.setDirector(params.getParameter("director"));
        try {
            movie.setPub_date(simpleDateFormat.parse(params.getParameter("pub_date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        movie.setTime_length(Integer.parseInt(params.getParameter("time_length")));
        movie.setRating(Float.parseFloat(params.getParameter("rating")));
        movie.setDescription(params.getParameter("description"));
        movie.setCategoryStr(params.getParameter("categoryStr"));
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String base64Str = Base64.encodeBase64String(file.getBytes());
                    movie.setPic(base64Str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        int flag = movieService.saveMovie(movie);
        if (flag < 1) {
            return new R(Code.WORK_ERR, "保存失败");
        }
        return new R(Code.WORK_OK, "保存成功");
    }

    @GetMapping("/findAllCategory")
    public R findAllCategory() {
        List<Category> allCategory = categoryService.findAll();
        if (allCategory != null) {
            return new R(Code.WORK_OK, "查询成功", allCategory);
        }
        return new R(Code.WORK_ERR, "查询失败");
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
    @ResponseBody
    public R updateMovie(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(params.getParameter("id")));
        movie.setName(params.getParameter("name"));
        movie.setDirector(params.getParameter("director"));
        try {
            movie.setPub_date(simpleDateFormat.parse(params.getParameter("pub_date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        movie.setTime_length(Integer.parseInt(params.getParameter("time_length")));
        movie.setRating(Float.parseFloat(params.getParameter("rating")));
        movie.setDescription(params.getParameter("description"));
        movie.setCategoryStr(params.getParameter("categoryStr"));
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String base64Str = Base64.encodeBase64String(file.getBytes());
                    movie.setPic(base64Str);
                    FileLoad.upload(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        movieService.updateMovie(movie);
        return new R(Code.WORK_OK, "更新成功");
    }

    @PostMapping("/deleteMovie/{id}")
    public R deleteMovie(@PathVariable("id") int movieId) {
        movieService.deleteMovie(movieId);
        return new R(Code.WORK_OK, "删除成功");
    }

    @GetMapping("/playMovie")
    public R playMovie(int movieId) {
        Movie movie = movieService.findById(movieId);
        List<Movie> recommendMovieList = movieService.recommendMovieList(movieId);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movie", movie);
        dataMap.put("recommendMovieList", recommendMovieList);
        return new R(Code.WORK_OK, "完成推荐", dataMap);
    }
}
