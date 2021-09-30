package org.example.user.service.testdatabuilder;

import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;

public class UserTestDataBuilder {

    private Long id;
    private final Email email;
    private final Password password;

    public UserTestDataBuilder() {
        email = new Email("correo@correo.com");
        password = Password.of("Hello@#!?]");
    }

    public User build() {
        return new User(id, email, password);
    }
}
