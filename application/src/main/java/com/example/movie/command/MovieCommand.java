package com.example.movie.command;

import lombok.*;
import org.example.movie.model.AccessType;
import org.example.user.model.entity.User;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MovieCommand {
    private String name;
    private String resume;
    private int durationInMinutes;
    private LocalDate releaseDate;
    private String language;
    private AccessType accessType;
    @Setter
    private User user;
    @Setter
    private Long id;
}
