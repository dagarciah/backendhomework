package org.example.movie.service;

import org.example.movie.exception.InsufficientPrivilegesException;
import org.example.movie.exception.MovieNotFoundException;
import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;
import org.example.user.model.entity.User;

public class MovieDeleteService {
    private final MovieRepository repository;

    public MovieDeleteService(MovieRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Long id, User user) {
        Movie existing = repository.find(id)
                .orElseThrow(MovieNotFoundException::new);

        if (existing.cannotEdit(user)) {
            throw new InsufficientPrivilegesException();
        }

        return repository.delete(id);
    }
}
