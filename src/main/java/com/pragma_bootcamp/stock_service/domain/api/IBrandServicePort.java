package com.pragma_bootcamp.stock_service.domain.api;

import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PaginatedResult<Brand> getAllBrands(int page, int size, String sort);
}
