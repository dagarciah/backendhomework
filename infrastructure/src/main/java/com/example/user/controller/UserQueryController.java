package com.example.user.controller;

import com.example.user.query.handler.UserAuthenticationHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "User query")
public class UserQueryController {
    private final UserAuthenticationHandler userAuthenticationHandler;

    public UserQueryController(UserAuthenticationHandler userAuthenticationHandler) {
        this.userAuthenticationHandler = userAuthenticationHandler;
    }

    @GetMapping("/authentication")
    @ApiOperation("User authentication")
    public ResponseEntity<Void> authenticate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication) {
        String token = userAuthenticationHandler.execute(authentication);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
    }
}
