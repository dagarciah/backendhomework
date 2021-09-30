package com.example.user.query.handler;


import com.example.user.query.UserFactory;
import org.example.user.service.UserAuthenticationService;

public class UserAuthenticationHandler {
    private final UserFactory factory;
    private final UserAuthenticationService service;

    public UserAuthenticationHandler(UserFactory factory, UserAuthenticationService service) {
        this.factory = factory;
        this.service = service;
    }

    public String execute(String authentication) {
        return service.execute(factory.fromAuthenticationBasic(authentication));
    }
}
