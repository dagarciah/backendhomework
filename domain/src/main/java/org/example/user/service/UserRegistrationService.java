package org.example.user.service;

import org.example.user.exception.UserAlreadyExistsException;
import org.example.user.model.entity.User;
import org.example.user.port.repository.UserRepository;

public class UserRegistrationService {
    private final UserRepository repository;

    public UserRegistrationService(UserRepository repository) {
        this.repository = repository;
    }

    public Long execute(User user) {
        userAlreadyExistsValidation(user);
        return repository.create(user.toBuilder().password(HashingPasswordService.calculate(user.getPassword())).build());
    }

    private void userAlreadyExistsValidation(User user) {
        boolean exists = repository.exists(user.getEmail());
        if (exists) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
    }
}
