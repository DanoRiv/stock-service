package com.pragma_bootcamp.stock_service.domain.exception;

public class NegativeNumberNotAllowedException extends RuntimeException{
    private final String fieldName;

    public NegativeNumberNotAllowedException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), fieldName);
    }

}
