package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean alreadyExists(Category category);
    List<Category> getAllCategories(int page, int size, String sort);
}
