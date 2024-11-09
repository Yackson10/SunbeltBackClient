package com.sunbelt.backclient.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        String errorMessage = ex.getReason();

        return ResponseEntity.status(ex.getStatusCode())
                .body(Collections.singletonMap(MESSAGE, errorMessage));
    }
}
