package com.example.user.controller;

import com.example.command.CommandResponse;
import com.example.user.command.UserRegistrationCommand;
import com.example.user.command.handler.UserRegistrationHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "User command")
public class UserCommandController {

    private final UserRegistrationHandler registrationHandler;

    public UserCommandController(UserRegistrationHandler registrationHandler) {
        this.registrationHandler = registrationHandler;
    }

    @PostMapping
    @ApiOperation("User registration")
    public CommandResponse<Long> registration(@RequestBody UserRegistrationCommand command) {
        return registrationHandler.execute(command);
    }
}
