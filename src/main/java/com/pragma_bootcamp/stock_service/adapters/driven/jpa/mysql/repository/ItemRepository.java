package com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.repository;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String name);
}
