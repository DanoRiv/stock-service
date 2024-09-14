package com.pragma_bootcamp.stock_service.domain.util;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
    public enum Field {
        NAME,
        DESCRIPTION,
        QUANTITY,
        PRICE
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null";
    public static final String FIELD_BRAND_NULL_MESSAGE = "Field 'brand' cannot be null";

    public static final String DUPLICATED_CATEGORY_EXCEPTION_MESSAGE = "The category already exists";
    public static final String DUPLICATED_BRAND_EXCEPTION_MESSAGE = "The brand already exists";

    public static final String EMPTY_CATEGORIES_EXCEPTION_MESSAGE = "The item must have at least one category associated";
    public static final String CATEGORIES_NOT_FOUND_EXCEPTION_MESSAGE = "Could not find a category with id: ";
    public static final String BRAND_NOT_FOUND_EXCEPTION_MESSAGE = "Could not find a brand with id: ";

    public static final String NEGATIVE_NUMBER_EXCEPTION_MESSAGE = "Field '%s' cannot have a number below zero";

}
