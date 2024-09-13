package com.pragma_bootcamp.stock_service.domain.model;

import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE;
import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.FIELD_NAME_NULL_MESSAGE;
import static java.util.Objects.requireNonNull;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = requireNonNull(name, FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Category() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
