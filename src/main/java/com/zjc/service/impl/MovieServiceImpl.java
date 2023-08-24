package com.zjc.service.impl;

import com.zjc.mapper.MovieCategoryMapper;
import com.zjc.mapper.MovieMapper;
import com.zjc.mapper.RecommendTypeMapper;
import com.zjc.pojo.Category;
import com.zjc.pojo.Movie;
import com.zjc.pojo.MovieCategory;
import com.zjc.pojo.RecommendType;
import com.zjc.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieCategoryMapper movieCategoryMapper;
    @Autowired
    private RecommendTypeMapper recommendTypeMapper;

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

    @Override
    public List<Movie> recommendMovieList(int movieId) {
        Movie movie = movieMapper.findById(movieId);
        List<RecommendType> recommendList = recommendTypeMapper.findRecommendList();
        List<Movie> recommendMovieList = new ArrayList<>();
        Set<Integer> recommendMovieIds = new HashSet<>();
        for (RecommendType recommendtype : recommendList) {
            if (recommendtype.getId() == 1) {
                List<Movie> top3ByCates = movieMapper.findTop3ByCates(movieId);
                for (Movie m : top3ByCates) {
                    recommendMovieList.add(m);
                    recommendMovieIds.add(m.getId());
                }
            } else if (recommendtype.getId() == 2) {
                List<Movie> top3ByActors = movieMapper.findTop3ByActors(movieId);
                for (Movie m : top3ByActors) {
                    if (!recommendMovieIds.contains(m.getId())) {
                        recommendMovieList.add(m);
                        recommendMovieIds.add(m.getId());
                    }
                }
            } else {

            }
        }
        return recommendMovieList;
    }
}
