package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.MovieMapper;
import com.zjc.pojo.Movie;
import com.zjc.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;


    @Override
    public int addMovie(Movie movie) {
        Movie entity = new Movie();
        entity.setId(movie.getId());
        entity.setName(movie.getName());
        entity.setDirector(movie.getDirector());
        entity.setTime_length(movie.getTime_length());
        entity.setPub_date(movie.getPub_date());
        entity.setDescription(movie.getDescription());
        entity.setRating(movie.getRating());
        entity.setPic(movie.getPic());
        return movieMapper.insert(entity);
    }

    @Override
    public int deleteMovie(Integer id) {
        return movieMapper.deleteById(id);
    }

    @Override
    public int updateMovie(Movie movie) {
        Movie entity = new Movie();
        BeanUtils.copyProperties(movie, entity);
        return movieMapper.updateById(entity);
    }

    @Override
    public List<Movie> findMovie() {
        return movieMapper.findMovie();
    }
}
