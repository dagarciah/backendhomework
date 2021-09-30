package com.example.user.command.handler;

import com.example.command.CommandResponse;
import com.example.user.command.UserRegistrationCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;
import org.example.user.service.UserRegistrationService;

@Getter
@Builder
@AllArgsConstructor
public class UserRegistrationHandler {
    private final UserRegistrationService service;

    public CommandResponse<Long> execute(UserRegistrationCommand command) {
        Long id = service.execute(User.builder()
                .email(new Email(command.getEmail()))
                .password(Password.of(command.getPassword()))
                .build());

        return CommandResponse.of(id);
    }
}
