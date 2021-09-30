package org.example.like.service;

import org.example.like.model.Like;
import org.example.like.port.repository.LikeRepository;
import org.example.movie.exception.MovieNotFoundException;
import org.example.movie.port.repository.MovieRepository;

public class GiveLikeService {
    private final LikeRepository repository;
    private final MovieRepository movieRepository;

    public GiveLikeService(LikeRepository repository, MovieRepository movieRepository) {
        this.repository = repository;
        this.movieRepository = movieRepository;
    }

    public Long execute(Like like) {
        movieRepository.find(like.getMovieId())
                .orElseThrow(MovieNotFoundException::new);
        return repository.create(like);
    }
}
