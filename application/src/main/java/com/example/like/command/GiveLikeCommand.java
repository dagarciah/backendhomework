package com.example.like.command;

import lombok.*;
import org.example.user.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GiveLikeCommand {
    private User user;
    private Long id;
}
