package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Override
    public PaginatedResult<Category> getAllCategories(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, sortBy(sort));
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryEntityMapper.toModelList(categoryPage.getContent());
        if(categories.isEmpty()){
            throw new NoDataFoundException();
        }

        return new PaginatedResult<>(
                categories,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
    }

    private Sort sortBy(String sort){
        if(sort == null){
            return Sort.unsorted();
        }
        if(sort.equalsIgnoreCase("asc")){
            return Sort.by("name").ascending();
        }
        if(sort.equalsIgnoreCase("desc")){
            return Sort.by("name").descending();
        }
        return Sort.unsorted();
    }
}
