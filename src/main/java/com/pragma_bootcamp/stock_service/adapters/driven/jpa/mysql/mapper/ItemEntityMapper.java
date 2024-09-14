package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.ItemEntity;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ICategoryEntityMapper.class, IBrandEntityMapper.class})
public interface ItemEntityMapper {
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ItemEntity toEntity(Item item);
}
