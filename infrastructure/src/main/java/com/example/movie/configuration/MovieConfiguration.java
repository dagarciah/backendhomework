package com.example.movie.configuration;

import com.example.error.ExceptionStatus;
import com.example.movie.command.factory.MovieFactory;
import com.example.movie.command.handler.*;
import org.example.movie.exception.InsufficientPrivilegesException;
import org.example.movie.exception.MovieAlreadyExistsException;
import org.example.movie.exception.MovieNotFoundException;
import org.example.movie.port.dao.MovieDao;
import org.example.movie.port.repository.MovieRepository;
import org.example.movie.service.MovieCreateService;
import org.example.movie.service.MovieDeleteService;
import org.example.movie.service.MovieUpdateService;
import org.example.user.port.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfiguration {
    @Bean
    MovieFactory movieFactory(UserRepository repository) {
        return new MovieFactory(repository);
    }

    @Bean
    MovieDeleteHandler movieDeleteHandler(MovieDeleteService service) {
        return new MovieDeleteHandler(service);
    }

    @Bean
    MovieUpdateHandler movieUpdateHandler(MovieFactory factory, MovieUpdateService service) {
        return new MovieUpdateHandler(factory, service);
    }

    @Bean
    MovieCreteHandler movieCreteHandler(MovieFactory factory, MovieCreateService service) {
        return new MovieCreteHandler(factory, service);
    }

    @Bean
    MovieListHandler movieListHandler(MovieDao dao, UserRepository repository) {
        return new MovieListHandler(dao, repository);
    }

    @Bean
    MovieFindHandler movieFindHandler(MovieDao dao, UserRepository repository) {
        return new MovieFindHandler(dao, repository);
    }

    @Bean
    MovieCreateService movieCreateService(MovieRepository repository) {
        return new MovieCreateService(repository);
    }

    @Bean
    MovieUpdateService movieUpdateService(MovieRepository repository) {
        return new MovieUpdateService(repository);
    }

    @Bean
    MovieDeleteService movieDeleteService(MovieRepository repository) {
        return new MovieDeleteService(repository);
    }


    @Bean
    ExceptionStatus insufficientPrivilegesException() {
        return ExceptionStatus.with(InsufficientPrivilegesException.class.getSimpleName(), 401);
    }

    @Bean
    ExceptionStatus movieAlreadyExistsException() {
        return ExceptionStatus.with(MovieAlreadyExistsException.class.getSimpleName(), 400);
    }

    @Bean
    ExceptionStatus movieNotFoundException() {
        return ExceptionStatus.with(MovieNotFoundException.class.getSimpleName(), 400);
    }
}
