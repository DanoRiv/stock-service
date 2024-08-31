package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}