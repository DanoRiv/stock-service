package com.pragma_bootcamp.stock_service.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final String MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE = "The length of the field must not exceed {max} characters";
    public static final String DUPLICATE_CATEGORY_EXCEPTION_MESSAGE = "The category already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The field %s cannot be empty";
}
