package com.pragma_bootcamp.stock_service.domain.api;

import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PaginatedResult<Category> getAllCategories(int page, int size, String sort);
}
