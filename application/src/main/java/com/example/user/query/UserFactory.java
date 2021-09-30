package com.example.user.query;

import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;
import org.example.user.service.HashingPasswordService;

import java.util.Base64;

public class UserFactory {
    private final Base64.Decoder decoder = Base64.getDecoder();

    public User fromAuthenticationBasic(String authentication) {
        String[] tuple = new String(decoder.decode(authentication.replace("Basic ", ""))).split(":");

        return User.builder()
                .email(new Email(tuple[0]))
                .password(HashingPasswordService.calculate(Password.of(tuple[1])))
                .build();
    }
}
