package com.pragma_bootcamp.stock_service.configuration.exceptionhandler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private final String message;
    private final String status;
    private final LocalDateTime time;

    public ExceptionResponse(String message, String status, LocalDateTime time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }
}
