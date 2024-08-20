package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.DuplicatedEntryException;
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
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new DuplicatedEntryException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
