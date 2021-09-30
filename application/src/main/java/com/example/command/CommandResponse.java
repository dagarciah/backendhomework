package com.example.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CommandResponse <T> {
    private final T value;

    public static CommandResponse<Void> empty() {
        return CommandResponse.of(null);
    }
}
