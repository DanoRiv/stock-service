package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.CategoryRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_bootcamp.stock_service.domain.api.ICategoryServicePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import jakarta.validation.Valid;
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
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryServicePort.saveCategory(categoryRequestMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<PaginatedResult<CategoryResponse>> getAllCategories(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort){
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page, size, sort)));
    }
}
