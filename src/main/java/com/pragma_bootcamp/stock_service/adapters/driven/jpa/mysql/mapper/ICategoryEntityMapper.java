package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    List<Category> toModelList(List<CategoryEntity> categoryEntities);
    CategoryEntity toEntity(Category category);
}