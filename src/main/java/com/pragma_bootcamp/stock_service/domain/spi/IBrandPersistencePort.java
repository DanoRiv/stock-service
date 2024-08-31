package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean alreadyExists(Brand brand);
}
