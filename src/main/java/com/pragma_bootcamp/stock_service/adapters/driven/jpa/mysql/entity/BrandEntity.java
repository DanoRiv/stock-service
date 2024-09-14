package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma_bootcamp.stock_service.configuration.Constants.*;

@Entity
@Table(name="brand")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    @Size(max = MAX_CHARACTERS_BRAND_NAME, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String name;
    @Column(nullable = false)
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    @Size(max = MAX_CHARACTERS_BRAND_DESCRIPTION, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String description;
}
