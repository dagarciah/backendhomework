package com.example.command;

public interface CommandHandler<C, R> {
    CommandResponse<R> execute(C command);
}
