package com.zjc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.mapper.MovieActorMapper;
import com.zjc.mapper.MovieMapper;
import com.zjc.pojo.Movie;
import com.zjc.pojo.MovieActor;
import com.zjc.pojo.MovieCategory;
import com.zjc.service.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieActorServiceImpl implements MovieActorService {

    @Autowired
    private MovieActorMapper movieActorMapper;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public int saveMovieActor(Movie movie) {
        int flag = 0;
        int mid = movie.getId();
        String actorIds = movie.getActorIds();
        String[] actorIdArray = actorIds.split(",");
        for (String aids : actorIdArray) {
            int aid = Integer.parseInt(aids);
            MovieActor entity = new MovieActor();
            entity.setMovieid(mid);
            entity.setActorid(aid);
            flag += movieActorMapper.insert(entity);
        }
        return flag;
    }

    @Override
    public void deleteByMovieId(int id) {
        movieMapper.deleteMovieActor(id);
    }
}
