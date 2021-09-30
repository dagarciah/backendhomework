package com.example.like.command.factory;

import com.example.like.command.GiveLikeCommand;
import org.example.like.model.Like;

public class LikeFactory {
    public Like create(GiveLikeCommand command) {
        return Like.from(command.getId(), command.getUser());
    }
}
