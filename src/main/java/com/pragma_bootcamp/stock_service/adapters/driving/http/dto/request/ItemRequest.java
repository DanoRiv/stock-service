package com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class ItemRequest {
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private Long brandId;
    @UniqueElements
    private List<Long> categoriesId;
}
