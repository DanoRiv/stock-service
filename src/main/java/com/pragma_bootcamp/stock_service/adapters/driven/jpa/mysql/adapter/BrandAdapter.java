package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public boolean alreadyExists(Brand brand) {
        return brandRepository.findByName(brand.getName()).isPresent();
    }
}
