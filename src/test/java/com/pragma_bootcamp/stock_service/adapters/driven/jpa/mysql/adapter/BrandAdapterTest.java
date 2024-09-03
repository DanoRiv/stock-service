package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandAdapterTest {
    @Mock
    private IBrandRepository brandRepository;
    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @Test
    void shouldSaveBrandSuccessfully() {
        Brand brand = new Brand(1L, "Apple", "Computer and cellphone brand");
        BrandEntity brandEntity = new BrandEntity(1L, "Apple", "Computer and cellphone brand");
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);
        when(brandRepository.save(brandEntity)).thenReturn(brandEntity);

        brandAdapter.saveBrand(brand);

        verify(brandEntityMapper, times(1)).toEntity(brand);
        verify(brandRepository, times(1)).save(brandEntity);

        assertEquals(brand.getName(), brandEntity.getName());
    }

    @Test
    void shouldReturnTrueIfBrandAlreadyExists (){
        Brand brand = new Brand(1L, "Apple", "Computer and cellphone brand");
        BrandEntity brandEntity = new BrandEntity(1L, "Apple", "Computer and cellphone brand");
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.of(brandEntity));

        assertTrue(brandAdapter.alreadyExists(brand));
        assertEquals(brand.getName(), brandEntity.getName());

        verify(brandRepository, times(1)).findByName(brand.getName());

    }
    @Test
    void shouldReturnFalseIfBrandAlreadyExists (){
        Brand brand = new Brand(1L, "Apple", "Computer and cellphone brand");

        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.empty());

        assertFalse(brandAdapter.alreadyExists(brand));

        verify(brandRepository, times(1)).findByName(brand.getName());
    }
}