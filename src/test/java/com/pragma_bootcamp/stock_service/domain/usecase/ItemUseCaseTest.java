package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.BrandNotFoundException;
import com.pragma_bootcamp.stock_service.domain.exception.CategoryNotFoundException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.exception.NegativeNumberNotAllowedException;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.spi.IItemPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemUseCaseTest {
    @Mock
    private IItemPersistencePort itemPersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private ItemUseCase itemUseCase;

    private Brand brand;
    private Category category;

    @BeforeEach
    public void setUp(){
        brand = new Brand(1L, "Norma", "Norma books brand");
        category = new Category(1L, "Books", "Books item");
    }

    @Test
    void shouldSaveItemSuccessfully() {

        when(categoryPersistencePort.getCategoryById(category.getId()))
                .thenReturn(Optional.of(category));

        when(brandPersistencePort.getBrandById(brand.getId()))
                .thenReturn(Optional.of(brand));

        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", 30, BigDecimal.valueOf(50000), brand, Set.of(category));

        doNothing().when(itemPersistencePort).saveItem(any(Item.class));


        itemUseCase.saveItem(item);

        verify(itemPersistencePort, times(1)).saveItem(item);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {

        Item item = new Item(1L, "", "Some description about the book", 30, BigDecimal.valueOf(50000), brand, Set.of(category));

        Exception exception = assertThrows(EmptyFieldException.class, () -> itemUseCase.saveItem(item));

        assertEquals("NAME", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {

        Item item = new Item(1L, "Pride and Prejudice", "", 30, BigDecimal.valueOf(50000), brand, Set.of(category));

        Exception exception = assertThrows(EmptyFieldException.class, () -> itemUseCase.saveItem(item));

        assertEquals("DESCRIPTION", exception.getMessage());
    }

    @Test
    void ShouldThrowExceptionWhenQuantityIsNegative(){
        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", -30, BigDecimal.valueOf(50000), brand, Set.of(category));

        assertThrows(NegativeNumberNotAllowedException.class, ()-> itemUseCase.saveItem(item));
        assertTrue(item.getQuantity() < 0);
    }

    @Test
    void ShouldThrowExceptionWhenPriceIsNegative(){
        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", 30, BigDecimal.valueOf(-50000), brand, Set.of(category));

        assertThrows(NegativeNumberNotAllowedException.class, ()-> itemUseCase.saveItem(item));
        assertTrue(item.getPrice().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void shouldThrowExceptionWhenCategoriesIsEmpty(){
        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", 30, BigDecimal.valueOf(50000), brand, Collections.emptySet());

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> itemUseCase.saveItem(item));
        assertEquals(EMPTY_CATEGORIES_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBrandNotFound(){
        when(categoryPersistencePort.getCategoryById(category.getId()))
                .thenReturn(Optional.of(category));
        when(brandPersistencePort.getBrandById(brand.getId()))
                .thenReturn(Optional.empty());

        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", 30, BigDecimal.valueOf(50000), brand, Set.of(category));

        Exception exception = assertThrows(BrandNotFoundException.class, ()-> itemUseCase.saveItem(item));
        assertEquals(BRAND_NOT_FOUND_EXCEPTION_MESSAGE + brand.getId(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound(){
        when(categoryPersistencePort.getCategoryById(category.getId()))
                .thenReturn(Optional.empty());

        Item item = new Item(1L, "Pride and Prejudice", "Some description about the book", 30, BigDecimal.valueOf(50000), brand, Set.of(category));

        Exception exception = assertThrows(CategoryNotFoundException.class, ()-> itemUseCase.saveItem(item));
        assertEquals(CATEGORIES_NOT_FOUND_EXCEPTION_MESSAGE + category.getId(), exception.getMessage());

    }
}