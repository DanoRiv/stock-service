package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma_bootcamp.stock_service.configuration.Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
import static com.pragma_bootcamp.stock_service.configuration.Constants.MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE;

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
    @Size(max = 50, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String name;
    @Column(nullable = false)
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    @Size(max = 120, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String description;

}
