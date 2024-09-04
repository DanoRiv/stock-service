package com.pragma_bootcamp.stock_service.domain.api;

import com.pragma_bootcamp.stock_service.domain.model.Item;

public interface IItemServicePort {
    void saveItem(Item item);
}
