package com.pragma_bootcamp.stock_service.domain.model;

import java.math.BigDecimal;
import java.util.Set;

public class Item {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer quantity;
    private final BigDecimal price;
    private final Brand brand;
    private final Set<Category> categories;

    public Item(Long id, String name, String description, Integer quantity, BigDecimal price, Brand brand, Set<Category> categories) {
        amountOfCategoriesValidation(categories);
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    private void amountOfCategoriesValidation(Set<Category> categories){
        if(categories == null || categories.isEmpty() || categories.size() > 3) {
            throw new IllegalArgumentException();
        }
    }
}
