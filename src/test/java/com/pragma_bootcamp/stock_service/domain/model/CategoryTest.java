package com.pragma_bootcamp.stock_service.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        assertThrows(NullPointerException.class, () ->  new Category(1L, null, "Some description"));
    }
}