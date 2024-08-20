package com.pragma_bootcamp.stock_service.adapters.driving.http.mapper;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);
}
