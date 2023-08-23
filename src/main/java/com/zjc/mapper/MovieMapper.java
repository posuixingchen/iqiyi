package com.zjc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper extends BaseMapper<Movie> {
    List<Movie> findMovie();

    int saveMovie(Movie movie);

    Movie findById(int movieId);

    int deleteMovieCategories(Integer id);

    int deleteMovieActor(Integer id);

    int deleteMovie(Integer id);

    int updateMovie(Movie movie);
}
