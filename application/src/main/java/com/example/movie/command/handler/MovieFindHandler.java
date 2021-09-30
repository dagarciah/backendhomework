package com.example.movie.command.handler;

import org.example.movie.model.Movie;
import org.example.movie.port.dao.MovieDao;
import org.example.user.model.entity.User;
import org.example.user.port.repository.UserRepository;

public class MovieFindHandler {
    private final MovieDao dao;
    private final UserRepository repository;

    public MovieFindHandler(MovieDao dao, UserRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    public Movie execute(Long id, User user) {
        return dao.find(id, repository.findBy(user.getEmail()));
    }
}
