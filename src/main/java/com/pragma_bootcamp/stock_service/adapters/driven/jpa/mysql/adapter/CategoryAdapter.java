package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean alreadyExists(Category category) {
        return categoryRepository.findByName(category.getName()).isPresent();
    }
}
