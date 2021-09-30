package com.example.movie.controller;

import com.example.command.CommandResponse;
import com.example.movie.command.MovieCommand;
import com.example.movie.command.MovieDeleteCommand;
import com.example.movie.command.handler.MovieCreteHandler;
import com.example.movie.command.handler.MovieDeleteHandler;
import com.example.movie.command.handler.MovieUpdateHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.user.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/movie")
@Api(tags = "Movie command")
public class MovieCommandController {

    private final MovieCreteHandler movieCreateHandler;
    private final MovieUpdateHandler movieUpdateHandler;
    private final MovieDeleteHandler movieDeleteHandler;

    public MovieCommandController(
            MovieCreteHandler movieCreateHandler,
            MovieUpdateHandler movieUpdateHandler,
            MovieDeleteHandler movieDeleteHandler) {
        this.movieCreateHandler = movieCreateHandler;
        this.movieUpdateHandler = movieUpdateHandler;
        this.movieDeleteHandler = movieDeleteHandler;
    }

    @PostMapping
    @ApiOperation(value = "Create a new movie", authorizations = @Authorization("JWT"))
    public CommandResponse<Long> create(@RequestBody MovieCommand command, @ApiIgnore Authentication authentication) {
        command.setUser((User) authentication.getPrincipal());
        return movieCreateHandler.execute(command);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing movie", authorizations = @Authorization("JWT"))
    public CommandResponse<Void> update(@RequestBody MovieCommand command, @PathVariable("id") Long id, @ApiIgnore Authentication authentication) {
        command.setId(id);
        command.setUser((User) authentication.getPrincipal());
        return movieUpdateHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing movie", authorizations = @Authorization("JWT"))
    public CommandResponse<Boolean> delete(@PathVariable("id") Long id, @ApiIgnore Authentication authentication) {
        MovieDeleteCommand command = MovieDeleteCommand.builder().id(id).user((User) authentication.getPrincipal()).build();
        return movieDeleteHandler.execute(command);
    }
}
