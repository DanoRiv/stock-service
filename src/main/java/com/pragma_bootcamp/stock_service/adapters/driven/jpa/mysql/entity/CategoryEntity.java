package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pragma_bootcamp.stock_service.configuration.Constants.MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE;

@Entity
@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(max = 50, message = "The length of {0} cannot exceed {max} characters")
    private String name;
    @Column(nullable = false)
    @Size(max = 90, message = MAX_CHARACTER_LENGTH_EXCEPTION_MESSAGE)
    private String description;
}
