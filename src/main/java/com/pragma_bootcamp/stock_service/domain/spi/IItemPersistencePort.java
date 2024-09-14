package com.pragma_bootcamp.stock_service.domain.spi;

import com.pragma_bootcamp.stock_service.domain.model.Item;

public interface IItemPersistencePort {
    void saveItem(Item item);
}
