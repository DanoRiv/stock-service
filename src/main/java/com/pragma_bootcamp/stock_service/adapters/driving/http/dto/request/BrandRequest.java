package com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.pragma_bootcamp.stock_service.configuration.Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
import static com.pragma_bootcamp.stock_service.configuration.Constants.MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE;

@Getter
@Setter
public class BrandRequest {
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    @Size(max = 50, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String name;
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    @Size(max = 120, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String description;
}
