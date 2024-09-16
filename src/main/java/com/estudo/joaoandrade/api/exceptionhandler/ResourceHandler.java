package com.estudo.joaoandrade.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenerico(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status.value()).body(new ProblemDetail(LocalDateTime.now(), ex.getMessage(), status.value()));
    }

}
