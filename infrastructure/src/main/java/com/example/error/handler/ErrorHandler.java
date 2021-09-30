package com.example.error.handler;

import com.example.error.Error;
import com.example.error.ExceptionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {
    private final Map<String, Integer> statusMap;

    public ErrorHandler(List<ExceptionStatus> status) {
        this.statusMap = status.stream().collect(Collectors.toMap(ExceptionStatus::getType, ExceptionStatus::getStatus));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllException(Exception exception) {
        String exceptionName = exception.getClass().getSimpleName();
        return ResponseEntity.status(statusMap.getOrDefault(exceptionName, 500))
                .body(Error.with(exceptionName, exception.getMessage()));
    }
}
