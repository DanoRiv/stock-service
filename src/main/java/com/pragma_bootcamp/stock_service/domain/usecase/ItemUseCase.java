package com.pragma_bootcamp.stock_service.domain.usecase;

import com.pragma_bootcamp.stock_service.domain.api.IItemServicePort;
import com.pragma_bootcamp.stock_service.domain.exception.*;
import com.pragma_bootcamp.stock_service.domain.model.Brand;
import com.pragma_bootcamp.stock_service.domain.model.Category;
import com.pragma_bootcamp.stock_service.domain.model.Item;
import com.pragma_bootcamp.stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_bootcamp.stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_bootcamp.stock_service.domain.spi.IItemPersistencePort;
import com.pragma_bootcamp.stock_service.domain.util.DomainConstants;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.EMPTY_CATEGORIES_EXCEPTION_MESSAGE;

public class ItemUseCase implements IItemServicePort {

    private final IItemPersistencePort itemPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ItemUseCase(IItemPersistencePort itemPersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveItem(Item item) {
        if(item == null) throw new NullPointerException();
        if(item.getName().isBlank()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if(item.getDescription().isBlank()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(item.getQuantity() < 0) throw new NegativeNumberNotAllowedException(DomainConstants.NEGATIVE_NUMBER_EXCEPTION_MESSAGE, DomainConstants.Field.QUANTITY.toString());
        if(item.getPrice().compareTo(BigDecimal.ZERO) < 0) throw new NegativeNumberNotAllowedException(DomainConstants.NEGATIVE_NUMBER_EXCEPTION_MESSAGE, DomainConstants.Field.PRICE.toString());
        Set<Category> categories = Optional.ofNullable(item.getCategories())
                .orElse(Collections.emptySet()).stream()
                .map(category -> categoryPersistencePort.getCategoryById(category.getId())
                            .orElseThrow(() -> new CategoryNotFoundException(DomainConstants.CATEGORIES_NOT_FOUND_EXCEPTION_MESSAGE + category.getId()))
                )
                .collect(Collectors.toSet());
        if(categories.isEmpty()) throw new IllegalArgumentException(EMPTY_CATEGORIES_EXCEPTION_MESSAGE);
        item.setCategories(categories);

        Long brandId= item.getBrand().getId();
        Brand emptyBrand = new Brand();
        item.setBrand(emptyBrand);
        emptyBrand.setId(brandId);

        Brand fullBrand = brandPersistencePort.getBrandById(brandId)
                .orElseThrow(()-> new BrandNotFoundException(DomainConstants.BRAND_NOT_FOUND_EXCEPTION_MESSAGE + brandId));
        item.setBrand(fullBrand);

        itemPersistencePort.saveItem(item);
    }
}
