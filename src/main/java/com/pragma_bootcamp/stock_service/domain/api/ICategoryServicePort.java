package com.pragma_bootcamp.stock_service.domain.api;

import com.pragma_bootcamp.stock_service.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
