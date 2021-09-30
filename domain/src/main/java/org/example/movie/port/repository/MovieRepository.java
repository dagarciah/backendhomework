package org.example.movie.port.repository;

import org.example.movie.model.Movie;

import java.util.Optional;

public interface MovieRepository {
    boolean matches(Movie movie);

    Long create(Movie movie);

    Optional<Movie> find(Long id);

    void update(Movie movie);

    boolean delete(Long id);
}
