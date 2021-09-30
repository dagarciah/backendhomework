package org.example.user.service;

import org.example.user.exception.UserInvalidCredentialsException;
import org.example.user.exception.UserNotFoundException;
import org.example.user.model.entity.User;
import org.example.user.port.dao.UserDao;
import org.example.user.port.factory.TokenFactory;

public class UserAuthenticationService {
    private final UserDao dao;
    private final TokenFactory factory;

    public UserAuthenticationService(UserDao dao, TokenFactory factory) {
        this.dao = dao;
        this.factory = factory;
    }

    public String execute(User user) {
        if (!dao.exists(user.getEmail())) {
            throw new UserNotFoundException(user.getEmail());
        }

        if (!dao.matches(user)) {
            throw new UserInvalidCredentialsException();
        }

        return factory.create(user);
    }

}
