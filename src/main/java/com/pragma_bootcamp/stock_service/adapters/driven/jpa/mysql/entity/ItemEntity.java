package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

import static com.pragma_bootcamp.stock_service.configuration.Constants.*;

@Entity
@Table(name="item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    private String name;
    @Column(nullable = false)
    @NotBlank(message = EMPTY_FIELD_EXCEPTION_MESSAGE)
    private String description;
    @Column(nullable = false)
    @Min(value = MIN_FIELD_VALUE, message = MIN_FIELD_VALUE_EXCEPTION_MESSAGE)
    private Integer quantity;
    @Column(nullable = false)
    @Min(value = MIN_FIELD_VALUE, message = MIN_FIELD_VALUE_EXCEPTION_MESSAGE)
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "item_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    @Size(min = MIN_CATEGORIES_PER_ITEM, max = MAX_CATEGORIES_PER_ITEM)
    private Set<CategoryEntity> categories;
}
