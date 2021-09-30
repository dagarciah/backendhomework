package com.example.movie.controller;

import com.example.movie.command.handler.MovieFindHandler;
import com.example.movie.command.handler.MovieListHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.movie.model.AccessType;
import org.example.movie.model.Movie;
import org.example.movie.model.MoviePage;
import org.example.user.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Api(tags = "Movie query")
public class MovieQueryController {

    private final MovieListHandler movieListHandler;
    private final MovieFindHandler movieFindHandler;

    public MovieQueryController(MovieListHandler movieListHandler, MovieFindHandler movieFindHandler) {
        this.movieListHandler = movieListHandler;
        this.movieFindHandler = movieFindHandler;
    }

    @GetMapping
    @ApiOperation(value = "list public and privates movies", authorizations = @Authorization("JWT"))
    public MoviePage list(@RequestParam(value = "accessType", required = false) AccessType[] accessTypes,
                          @RequestParam("page") Integer page, @RequestParam("size") Integer size, @ApiIgnore Authentication authentication) {
        return movieListHandler.execute(accessTypes, page, size, (User)authentication.getPrincipal());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find a movie by id", authorizations = @Authorization("JWT"))
    public Movie find(@PathVariable("id") Long id, @ApiIgnore Authentication authentication) {
        return movieFindHandler.execute(id, (User)authentication.getPrincipal());
    }

    @GetMapping("/liked")
    @ApiOperation(value = "list all public liked movies", authorizations = @Authorization("JWT"))
    public List<Movie> liked(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit, @ApiIgnore Authentication authentication) {
        return null;
    }
}
