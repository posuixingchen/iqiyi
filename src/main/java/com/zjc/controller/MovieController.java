package com.zjc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.pojo.User;
import com.zjc.service.CategoryService;
import com.zjc.service.MovieActorService;
import com.zjc.service.MovieCategoryService;
import com.zjc.service.MovieService;
import com.zjc.utils.Code;
import com.zjc.utils.R;
import org.apache.ibatis.javassist.CodeConverter;
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
    public List<Movie> findMovie() {
        return movieService.findMovie();
    }

    @PostMapping("/saveMovie")
    public R save(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestBody Movie movie) {

        File dir = new File(ImagesSavePath);
        //给文件重新设置一个名字
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        //创建新文件
        File newFile = new File(ImagesSavePath + newFileName);
        //复制操作
        try {
            String base64Str = Base64.encodeBase64String(file.getBytes());
            movie.setPic(base64Str);
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
            System.out.println("图片上传，访问URL：" + url);
            int flag = movieService.saveMovie(movie);
            if (flag != 1) {
                return new R(Code.WORK_ERR, "提交失败");
            }
            return new R(Code.WORK_OK, "提交成功");
        } catch (IOException e) {
            return new R(Code.WORK_ERR, "IO异常");
        }
    }

    @GetMapping("/findMovieOne/{id}")
    public R findMovieOne(@PathVariable("id") int movieId) {
        Movie movie = movieService.findById(movieId);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        List<Category> allCategory = categoryService.findAll(wrapper);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movie", movie);
        dataMap.put("allCategory", allCategory);
        if (dataMap != null) {
            return new R(Code.WORK_ERR, "查询失败");
        }
        return new R(Code.WORK_OK, "查询成功", dataMap);
    }

    @PostMapping("/updateMovie/{id}")
    public R updateMovie(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestBody Movie movie) {
        if (file != null) {
            File dir = new File(ImagesSavePath);
            //给文件重新设置一个名字
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

            //创建新文件
            File newFile = new File(ImagesSavePath + newFileName);
            //复制操作
            try {
                String base64Str = Base64.encodeBase64String(file.getBytes());
                movie.setPic(base64Str);
                file.transferTo(newFile);
                //协议 :// ip地址 ：端口号 / 文件目录(/images/xxx.jpg)
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
                System.out.println("图片上传，访问URL：" + url);
            } catch (IOException e) {
                return new R(Code.WORK_ERR, "IO异常");
            }
        }
        int flag = movieService.updateMovie(movie);
        if (flag != 1) {
            return new R(Code.WORK_ERR, "提交失败");
        }
        return new R(Code.WORK_OK, "提交成功");
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
