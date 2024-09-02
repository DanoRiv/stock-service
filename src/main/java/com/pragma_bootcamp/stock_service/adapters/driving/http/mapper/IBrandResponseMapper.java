package com.pragma_bootcamp.stock_service.adapters.driving.http.mapper;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.BrandResponse;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
    PaginatedResult<BrandResponse> toBrandResponseList(PaginatedResult<Brand> brands);
}
