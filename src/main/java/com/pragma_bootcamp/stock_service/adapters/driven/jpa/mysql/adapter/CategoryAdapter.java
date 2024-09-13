package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.util.Sorting;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.pragma_bootcamp.stock_service.configuration.Constants.SORT_BY_NAME;

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
        Sorting sorting = new Sorting();
        Pageable pageable = PageRequest.of(page, size, sorting.sortBy(SORT_BY_NAME, sort));
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

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryEntityMapper.toCategoryOptional(categoryRepository.findById(id));
    }
}
