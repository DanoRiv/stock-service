package com.pragma_bootcamp.stock_service.domain.exception;

public class DuplicatedEntryException extends RuntimeException{
    public DuplicatedEntryException(String message) {
        super(message);
    }
}
