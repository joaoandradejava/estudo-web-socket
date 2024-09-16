package com.estudo.joaoandrade.api.exceptionhandler;

import java.time.LocalDateTime;

public class ProblemDetail {
    private final LocalDateTime timestamp;
    private final String message;
    private final int status;

    public ProblemDetail(LocalDateTime timestamp, String message, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
