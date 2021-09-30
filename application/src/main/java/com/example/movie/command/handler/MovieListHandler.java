package com.example.movie.command.handler;

import org.example.movie.model.AccessType;
import org.example.movie.model.MoviePage;
import org.example.movie.port.dao.MovieDao;
import org.example.user.model.entity.User;
import org.example.user.port.repository.UserRepository;

public class MovieListHandler {
    private final MovieDao dao;
    private final UserRepository repository;

    public MovieListHandler(MovieDao dao, UserRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    public MoviePage execute(AccessType[] accessTypes, Integer page, Integer size, User user) {
        return dao.list(accessTypes, repository.findBy(user.getEmail()), page, size);
    }
}
