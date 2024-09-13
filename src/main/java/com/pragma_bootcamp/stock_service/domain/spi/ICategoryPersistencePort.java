package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean alreadyExists(Category category);
    PaginatedResult<Category> getAllCategories(int page, int size, String sort);
    Optional<Category> getCategoryById(Long id);
}
