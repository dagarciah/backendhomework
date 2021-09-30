package org.example.movie.port.dao;

import org.example.movie.model.AccessType;
import org.example.movie.model.Movie;
import org.example.movie.model.MoviePage;
import org.example.user.model.entity.User;

public interface MovieDao {
    MoviePage list(AccessType[] accessTypes, User user, int page, int size);

    Movie find(Long id, User by);
}
