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
        String categories = movie.getCategories();
        String[] cateids = categories.split(",");
        for (String cid : cateids) {
            int cateid = Integer.parseInt(cid);
            MovieCategory entity = new MovieCategory();
            entity.setMovieid(mid);
            entity.setCategoryid(cateid);
            movieCategoryMapper.insert(entity);
        }
        return flag;
    }

    @Override
    public int deleteMovie(Integer id) {
        int flag1 = movieMapper.deleteMovieCategories(id);
        int flag2 = movieMapper.deleteMovieActor(id);
        int flag3 = movieMapper.deleteMovie(id);
        if (flag1 == 0 || flag2 == 0 || flag3 == 0) {
            return 0;
        } else return 1;
    }

    @Override
    public int updateMovie(Movie movie) {
        int flag1 = movieMapper.updateMovie(movie);
        int flag2 = movieMapper.deleteMovieCategories(movie.getId());
        String categories = movie.getCategories();
        String[] cateids = categories.split(",");
        for (String cid : cateids) {
            int cateid = Integer.parseInt(cid);
            MovieCategory entity = new MovieCategory();
            entity.setMovieid(movie.getId());
            entity.setCategoryid(cateid);
            movieCategoryMapper.insert(entity);
        }
        if (flag1 == 0 || flag2 == 0) {
            return 0;
        }
        return 1;
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
