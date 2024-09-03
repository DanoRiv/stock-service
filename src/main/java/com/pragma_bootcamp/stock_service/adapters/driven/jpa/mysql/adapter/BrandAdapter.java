package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.util.Sorting;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.pragma_bootcamp.stock_service.configuration.Constants.SORT_BY_NAME;

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

    @Override
    public PaginatedResult<Brand> getAllBrands(int page, int size, String sort) {
        Sorting sorting = new Sorting();
        Pageable pageable = PageRequest.of(page, size, sorting.sortBy(SORT_BY_NAME, sort));
        Page<BrandEntity> brandPage = brandRepository.findAll(pageable);
        List<Brand> brands = brandEntityMapper.toBrandList(brandPage.getContent());
        if(brands.isEmpty()) throw new NoDataFoundException();

        return new PaginatedResult<>(
                brands,
                brandPage.getNumber(),
                brandPage.getSize(),
                brandPage.getTotalElements(),
                brandPage.getTotalPages()
        );
    }

}
