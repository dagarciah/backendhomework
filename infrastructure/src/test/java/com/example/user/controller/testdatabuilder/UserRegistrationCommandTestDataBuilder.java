package com.example.user.controller.testdatabuilder;

import com.example.user.command.UserRegistrationCommand;

public class UserRegistrationCommandTestDataBuilder {

    private String email;
    private String password;

    public UserRegistrationCommandTestDataBuilder() {
        email = "correo@correo.com";
        password = "Hello@#!?]";
    }

    public UserRegistrationCommand build() {
        return new UserRegistrationCommand(email, password);
    }

    public UserRegistrationCommandTestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRegistrationCommandTestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
}
