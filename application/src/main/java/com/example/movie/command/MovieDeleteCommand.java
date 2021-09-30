package com.example.movie.command;

import lombok.*;
import org.example.user.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MovieDeleteCommand {
    private User user;
    private Long id;
}
