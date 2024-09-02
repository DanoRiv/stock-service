package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.api.IBrandServicePort;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.DomainConstants;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if(brand.getName().isBlank()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if(brand.getDescription().isBlank()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(brandPersistencePort.alreadyExists(brand)) throw new DuplicatedEntryException(DomainConstants.DUPLICATED_BRAND_EXCEPTION_MESSAGE);

        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public PaginatedResult<Brand> getAllBrands(int page, int size, String sort) {
        return brandPersistencePort.getAllBrands(page, size, sort);
    }
}
