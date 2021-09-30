package com.example.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "with")
public class ExceptionStatus {
    private final String type;
    private final int status;
}
