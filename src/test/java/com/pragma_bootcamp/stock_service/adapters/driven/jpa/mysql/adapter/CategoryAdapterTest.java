package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @Test
    void shouldSaveCategorySuccessfully() {
        Category category = new Category(1L, "Books", "Books category");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Books", "Books category");
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);

        categoryAdapter.saveCategory(category);

        verify(categoryEntityMapper, times(1)).toEntity(category);
        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    void shouldReturnTrueIfCategoryAlreadyExists (){
        Category category = new Category(1L, "Books", "Books category");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Books", "Books category");
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(categoryEntity));

        assertTrue(categoryAdapter.alreadyExists(category));


        verify(categoryRepository, times(1)).findByName(category.getName());
    }
    @Test
    void shouldReturnFalseIfCategoryAlreadyExists (){
        Category category = new Category(1L, "Books", "Books category");

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());

        assertFalse(categoryAdapter.alreadyExists(category));

        verify(categoryRepository, times(1)).findByName(category.getName());
    }
}