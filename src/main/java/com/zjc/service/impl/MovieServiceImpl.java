package com.zjc.service.impl;

import com.zjc.mapper.MovieCategoryMapper;
import com.zjc.mapper.MovieMapper;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.pojo.MovieCategory;
import com.zjc.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieCategoryMapper movieCategoryMapper;

    @Override
    public int saveMovie(Movie movie) {
        int flag = 0;
        flag = movieMapper.saveMovie(movie);
        int mid = movie.getId();
        String categoryStr = movie.getCategoryStr();
        String[] cateids = categoryStr.split(",");
        for (String cid : cateids) {
            int cateid = Integer.parseInt(cid);
            MovieCategory entity = new MovieCategory();
            entity.setMovieid(mid);
            entity.setCategoryid(cateid);
            flag += movieCategoryMapper.insert(entity);
        }
        return flag;
    }

    @Override
    public void deleteMovie(Integer id) {
        movieMapper.deleteMovieCategories(id);
        movieMapper.deleteMovieActor(id);
        movieMapper.deleteMovie(id);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieMapper.updateMovie(movie);
        movieMapper.deleteMovieCategories(movie.getId());
        String categoryStr = movie.getCategoryStr();
        String[] cateids = categoryStr.split(",");
        for (String cid : cateids) {
            int cateid = Integer.parseInt(cid);
            MovieCategory entity = new MovieCategory();
            entity.setMovieid(movie.getId());
            entity.setCategoryid(cateid);
            movieCategoryMapper.insert(entity);
        }
    }

    @Override
    public List<Movie> findMovie() {
        return movieMapper.findMovie();
    }

    @Override
    public Movie findById(int movieId) {
        return movieMapper.findById(movieId);
    }
}
