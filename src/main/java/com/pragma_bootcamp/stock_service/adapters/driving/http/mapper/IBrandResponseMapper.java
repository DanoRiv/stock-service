package com.pragma_bootcamp.stock_service.adapters.driving.http.mapper;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.BrandResponse;
import com.pragma_bootcamp.stock_service.domain.model.Brand;

public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}
