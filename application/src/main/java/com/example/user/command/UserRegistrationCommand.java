package com.example.user.command;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserRegistrationCommand {
    private String email;
    private String password;
}
