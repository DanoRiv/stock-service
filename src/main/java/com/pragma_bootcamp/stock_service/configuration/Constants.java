package com.pragma_bootcamp.stock_service.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final String MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE = "The length of the field must not exceed {max} characters";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No results found in the database";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The field cannot be empty";
    public static final String SORT_BY_NAME = "name";


    public static final int MAX_CHARACTERS_CATEGORY_NAME = 50;
    public static final int MAX_CHARACTERS_CATEGORY_DESCRIPTION= 90;
    public static final int MAX_CHARACTERS_BRAND_NAME = 50;
    public static final int MAX_CHARACTERS_BRAND_DESCRIPTION = 120;
    public static final int MIN_CATEGORIES_PER_ITEM = 1;
    public static final int MAX_CATEGORIES_PER_ITEM = 3;

}
