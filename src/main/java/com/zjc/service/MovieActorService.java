package com.zjc.service;

import com.zjc.pojo.Movie;
import com.zjc.pojo.MovieActor;

public interface MovieActorService {
    int saveMovieActor(Movie movie);

    void deleteByMovieId(int id);
}
