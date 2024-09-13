package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    List<Category> toModelList(List<CategoryEntity> categoryEntities);
    @Mapping(target = "items", ignore = true)
    CategoryEntity toEntity(Category category);
    default Optional<Category> toCategoryOptional(Optional<CategoryEntity> categoryEntity){
        return categoryEntity.map(this::toModel);
    }
}
