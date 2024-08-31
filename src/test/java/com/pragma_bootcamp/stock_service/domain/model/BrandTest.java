package com.pragma_bootcamp.stock_service.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {
    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        assertThrows(NullPointerException.class, () ->  new Brand(1L, null, "Some description"));
    }
}