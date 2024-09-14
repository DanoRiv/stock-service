package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.mapper.ItemEntityMapper;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository.ItemRepository;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import com.pragma_bootcamp.stock_service.domain.spi.IItemPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemAdapter implements IItemPersistencePort {

    private final ItemRepository itemRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public void saveItem(Item item) {
        itemRepository.save(itemEntityMapper.toEntity(item));
    }
}
