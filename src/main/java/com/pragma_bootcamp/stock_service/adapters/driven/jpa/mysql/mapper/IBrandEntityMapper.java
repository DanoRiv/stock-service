package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);
    List<Brand> toBrandList(List<BrandEntity> brandEntities);
}
