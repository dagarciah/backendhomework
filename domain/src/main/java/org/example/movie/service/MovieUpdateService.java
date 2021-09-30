package org.example.movie.service;

import org.example.movie.exception.InsufficientPrivilegesException;
import org.example.movie.exception.MovieNotFoundException;
import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;

public class MovieUpdateService {
    private final MovieRepository repository;

    public MovieUpdateService(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute(Movie movie) {
        Movie existing = repository.find(movie.getId())
                .orElseThrow(MovieNotFoundException::new);

        if (existing.cannotEdit(movie.getCreator())) {
            throw new InsufficientPrivilegesException();
        }

        repository.update(movie);
    }
}
