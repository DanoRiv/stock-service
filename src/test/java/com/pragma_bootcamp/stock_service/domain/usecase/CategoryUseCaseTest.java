package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void shouldCreateCategorySuccessfully() {

        Category category = new Category(null, "Books", "Books Category");
        doNothing().when(categoryPersistencePort).saveCategory(any(Category.class));

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {

        Category category = new Category(null, "", "Some description");
        Exception exception = assertThrows(EmptyFieldException.class, () -> categoryUseCase.saveCategory(category));

        assertEquals("NAME", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {

        Category category = new Category(null, "TestName", "");
        Exception exception = assertThrows(EmptyFieldException.class, () -> categoryUseCase.saveCategory(category));

        assertEquals("DESCRIPTION", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsDuplicated(){
        // Arrange
        Category category = new Category(null, "ExistingName", "Some description");

        when(categoryPersistencePort.alreadyExists(category)).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicatedEntryException.class, () -> categoryUseCase.saveCategory(category));
    }
}