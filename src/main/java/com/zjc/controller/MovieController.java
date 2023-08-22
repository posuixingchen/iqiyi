package com.zjc.controller;

import com.zjc.pojo.Movie;
import com.zjc.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/findMovie")
    public List<Movie> findMovie() {
        return movieService.findMovie();
    }
}
