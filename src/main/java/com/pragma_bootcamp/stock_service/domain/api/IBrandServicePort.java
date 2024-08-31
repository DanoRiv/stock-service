package com.pragma_bootcamp.stock_service.domain.api;

import com.pragma_bootcamp.stock_service.domain.model.Brand;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
}
