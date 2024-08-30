package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void shouldReturnPaginatedResultOfAllCategories(){

        //arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");
        List<Category> content = Arrays.asList(category1, category2);

        long totalElements = 2;
        int totalPages = 1;
        int size = 2;
        int page = 0;

        PaginatedResult<Category> expectedResult = new PaginatedResult<>(content, page, size, totalElements, totalPages);

        when(categoryPersistencePort.getAllCategories(0, 2, "asc")).thenReturn(expectedResult);

        //act
        PaginatedResult<Category> actualResult = categoryUseCase.getAllCategories(0, 2, "asc");
        //assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
        assertEquals(content, actualResult.getContent());
        assertEquals(totalElements, actualResult.getTotalElements());
        assertEquals(totalPages, actualResult.getTotalPages());
        assertEquals(page, actualResult.getPage());
        assertEquals(size, actualResult.getSize());

        verify(categoryPersistencePort, times(1)).getAllCategories(0, 2, "asc");
    }
}