package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(
                        Map.of(
                                "status", ex.getStatus().value(),
                                "error", ex.getStatus().getReasonPhrase(),
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        Map.of(
                                "status", HttpStatus.METHOD_NOT_ALLOWED.value(),
                                "error", "Method Not Allowed",
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {

        HttpStatus status = (HttpStatus) ex.getStatusCode();

        return ResponseEntity
                .status(status)
                .body(
                        Map.of(
                                "status", status.value(),
                                "error", status.getReasonPhrase(),
                                "message", ex.getReason()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "error", ex.getClass().getSimpleName(),
                                "message", ex.getMessage()
                        )
                );
    }
}
