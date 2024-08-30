package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean alreadyExists(Category category);
    PaginatedResult<Category> getAllCategories(int page, int size, String sort);
}
