package com.example.like.controller;

import com.example.command.CommandResponse;
import com.example.like.command.GiveLikeCommand;
import com.example.like.command.handler.GiveLikeHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.example.user.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/like")
@Api(tags = "Like command")
public class LikeCommandController {

    private final GiveLikeHandler giveLikeHandler;

    public LikeCommandController(GiveLikeHandler giveLikeHandler) {
        this.giveLikeHandler = giveLikeHandler;
    }

    @PatchMapping("/{id}/like")
    @ApiOperation(value = "Update an existing movie", authorizations = @Authorization("JWT"))
    public CommandResponse<Long> like(@PathVariable("id") Long id, @ApiIgnore Authentication authentication) {
        GiveLikeCommand command = GiveLikeCommand.builder().id(id).user((User) authentication.getPrincipal()).build();
        return giveLikeHandler.execute(command);
    }

}
