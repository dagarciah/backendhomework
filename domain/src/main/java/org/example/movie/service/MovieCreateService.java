package org.example.movie.service;

import org.example.movie.exception.MovieAlreadyExistsException;
import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;

public class MovieCreateService {
    private final MovieRepository repository;

    public MovieCreateService(MovieRepository repository) {
        this.repository = repository;
    }

    public Long execute(Movie movie) {
        if (repository.matches(movie)) {
            throw new MovieAlreadyExistsException(movie);
        }

        return repository.create(movie);
    }
}
