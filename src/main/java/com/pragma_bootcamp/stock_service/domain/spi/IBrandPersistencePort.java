package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean alreadyExists(Brand brand);
    PaginatedResult<Brand> getAllBrands(int page, int size, String sort);
    Optional<Brand> getBrandById(Long id);
}
