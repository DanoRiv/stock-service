package com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
