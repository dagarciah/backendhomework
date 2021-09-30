package com.example.like.configuration;

import com.example.like.command.factory.LikeFactory;
import com.example.like.command.handler.GiveLikeHandler;
import org.example.like.port.repository.LikeRepository;
import org.example.like.service.GiveLikeService;
import org.example.movie.port.repository.MovieRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LikeConfiguration {
    @Bean
    LikeFactory likeFactory() {
        return new LikeFactory();
    }

    @Bean
    GiveLikeService giveLikeService(LikeRepository repository, MovieRepository movieRepository) {
        return new GiveLikeService(repository, movieRepository);
    }

    @Bean
    GiveLikeHandler giveLikeHandler(LikeFactory factory, GiveLikeService service) {
        return new GiveLikeHandler(factory, service);
    }
}
