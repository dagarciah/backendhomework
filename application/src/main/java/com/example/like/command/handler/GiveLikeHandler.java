package com.example.like.command.handler;

import com.example.command.CommandHandler;
import com.example.command.CommandResponse;
import com.example.like.command.GiveLikeCommand;
import com.example.like.command.factory.LikeFactory;
import org.example.like.service.GiveLikeService;

public class GiveLikeHandler implements CommandHandler<GiveLikeCommand, Long> {

    private final LikeFactory factory;
    private final GiveLikeService service;

    public GiveLikeHandler(LikeFactory factory, GiveLikeService service) {
        this.factory = factory;
        this.service = service;
    }

    public CommandResponse<Long> execute(GiveLikeCommand command) {
        return CommandResponse.of(service.execute(factory.create(command)));
    }
}
