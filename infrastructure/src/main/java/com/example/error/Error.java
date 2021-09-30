package com.example.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "with")
public class Error {
    private final String type;
    private final String message;
}
