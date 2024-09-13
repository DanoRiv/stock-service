package com.pragma_bootcamp.stock_service.domain.model;

import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE;
import static com.pragma_bootcamp.stock_service.domain.util.DomainConstants.FIELD_NAME_NULL_MESSAGE;
import static java.util.Objects.requireNonNull;

public class Brand {
    private Long id;
    private String name;
    private String description;

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = requireNonNull(name, FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, FIELD_DESCRIPTION_NULL_MESSAGE);
    }

    public Brand() {

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
