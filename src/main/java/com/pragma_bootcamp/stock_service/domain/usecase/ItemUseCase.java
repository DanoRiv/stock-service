package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.api.IItemServicePort;
import com.pragma_bootcamp.stock_service.domain.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import com.pragma_bootcamp.stock_service.domain.spi.IItemPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.DomainConstants;

public class ItemUseCase implements IItemServicePort {

    private final IItemPersistencePort itemPersistencePort;

    public ItemUseCase(IItemPersistencePort itemPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public void saveItem(Item item) {
        if(item == null) throw new NullPointerException();
        if(item.getName().isBlank()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if(item.getDescription().isBlank()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(itemPersistencePort.alreadyExist(item)) throw new DuplicatedEntryException(DomainConstants.DUPLICATED_ITEM_EXCEPTION_MESSAGE);
        itemPersistencePort.saveItem(item);
    }
}
