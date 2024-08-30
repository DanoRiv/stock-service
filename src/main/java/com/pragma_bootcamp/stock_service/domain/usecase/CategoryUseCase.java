package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.api.ICategoryServicePort;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.DomainConstants;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if(category.getName().isBlank()){
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(category.getDescription().isBlank()){
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if(categoryPersistencePort.alreadyExists(category)){
            throw new DuplicatedEntryException(DomainConstants.DUPLICATED_CATEGORY_EXCEPTION_MESSAGE);
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public PaginatedResult<Category> getAllCategories(int page, int size, String sort) {
        return categoryPersistencePort.getAllCategories(page, size, sort);
    }

}
