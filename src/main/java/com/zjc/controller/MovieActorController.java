package com.zjc.controller;

import com.zjc.pojo.Actor;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.service.ActorService;
import com.zjc.service.MovieActorService;
import com.zjc.service.MovieService;
import com.zjc.utils.Code;
import com.zjc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("MovieActor")
public class MovieActorController {

    @Autowired
    private MovieActorService movieActorService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;

    @PostMapping("/saveMovieActor")
    public R saveMovieActor(@RequestBody Movie movie) {
        int flag = movieActorService.saveMovieActor(movie);
        if (flag != 0) {
            return new R(Code.WORK_OK, "保存成功");
        }
        return new R(Code.WORK_ERR, "保存失败");
    }

    @GetMapping("/findMovieActor/{id}")
    public R findMovieActor(@PathVariable("id") int movieId) {
        Movie movie = movieService.findById(movieId);
        List<Movie> movieList = movieService.findMovie();
        List<Actor> actorLIst = actorService.findActor();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movie", movie);
        dataMap.put("movieList", movieList);
        dataMap.put("actorLIst", actorLIst);
        if (dataMap != null) {
            return new R(Code.WORK_OK, "查询成功", dataMap);
        }
        return new R(Code.WORK_ERR, "查询失败");
    }

    @PostMapping("/updateMovieActor")
    public R updateMovieActor(@RequestBody Movie movie) {
        movieActorService.deleteByMovieId(movie.getId());
        int flag = movieActorService.saveMovieActor(movie);
        if (flag != 0) {
            return new R(Code.WORK_OK, "更新成功");
        }
        return new R(Code.WORK_ERR, "更新失败");
    }
}
