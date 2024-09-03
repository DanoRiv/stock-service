package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    void shouldCreateBrandSuccessfully() {

        Brand brand = new Brand(null, "Coca cola", "Soda brand");
        doNothing().when(brandPersistencePort).saveBrand(any(Brand.class));

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {

        Brand brand = new Brand(null, "", "Some description");
        Exception exception = assertThrows(EmptyFieldException.class, () -> brandUseCase.saveBrand(brand));

        assertEquals("NAME", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {

        Brand brand = new Brand(null, "TestName", "");
        Exception exception = assertThrows(EmptyFieldException.class, () -> brandUseCase.saveBrand(brand));

        assertEquals("DESCRIPTION", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsDuplicated(){
        // Arrange
        Brand brand = new Brand(null, "ExistingName", "Some description");

        when(brandPersistencePort.alreadyExists(brand)).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicatedEntryException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldReturnPaginatedResultOfAllBrands(){

        //arrange
        Brand brand1 = new Brand(1L, "Coca cola", "Soda brand");
        Brand brand2 = new Brand(2L, "Renault", "Cars brand");
        List<Brand> content = Arrays.asList(brand1, brand2);

        long totalElements = 2;
        int totalPages = 1;
        int size = 2;
        int page = 0;

        PaginatedResult<Brand> expectedResult = new PaginatedResult<>(content, page, size, totalElements, totalPages);

        when(brandPersistencePort.getAllBrands(0, 2, "asc")).thenReturn(expectedResult);

        //act
        PaginatedResult<Brand> actualResult = brandUseCase.getAllBrands(0, 2, "asc");
        //assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
        assertEquals(content, actualResult.getContent());
        assertEquals(totalElements, actualResult.getTotalElements());
        assertEquals(totalPages, actualResult.getTotalPages());
        assertEquals(page, actualResult.getPage());
        assertEquals(size, actualResult.getSize());

        verify(brandPersistencePort, times(1)).getAllBrands(0, 2, "asc");
    }
}