package com.pragma_bootcamp.stock_service.adapters.driving.http.mapper;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.ItemRequest;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import java.util.Collections;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ICategoryRequestMapper.class)
public interface ItemRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "brandIdToBrand")
    @Mapping(target = "categories", source = "categoriesId", qualifiedByName = "categoryIdsToCategories")
    Item toItem(ItemRequest itemRequest);

    @Named("brandIdToBrand")
    default Brand brandIdToBrand(Long id){
        if(id == null) return null;
        Brand brand = new Brand();
        brand.setId(id);
        return brand;
    }

    @Named("categoryIdsToCategories")
    default Set<Category> categoryIdsToCategories(List<Long> categoryIds){
        if(categoryIds == null) return Collections.emptySet();
        return categoryIds.stream().map(id-> {
            Category category = new Category();
            category.setId(id);
            return category;
        }).collect(Collectors.toSet());

    }
}
