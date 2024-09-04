package com.pragma_bootcamp.stock_service.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
    public enum Field {
        NAME,
        DESCRIPTION
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";

    public static final String DUPLICATED_CATEGORY_EXCEPTION_MESSAGE = "The category already exists";
    public static final String DUPLICATED_BRAND_EXCEPTION_MESSAGE = "The brand already exists";
    public static final String DUPLICATED_ITEM_EXCEPTION_MESSAGE = "The item already exists";
}
