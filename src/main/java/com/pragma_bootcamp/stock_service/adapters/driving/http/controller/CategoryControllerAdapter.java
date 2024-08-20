package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.CategoryRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_bootcamp.stock_service.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryServicePort.saveCategory(categoryRequestMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
