package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.CategoryRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.CategoryResponse;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_bootcamp.stock_service.configuration.exceptionhandler.ExceptionResponse;
import com.pragma_bootcamp.stock_service.domain.api.ICategoryServicePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create new category")
            @ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryRequest.class)))
            @ApiResponse(responseCode = "400", description = "Couldn't create category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryServicePort.saveCategory(categoryRequestMapper.toCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a paginated list of categories")
    @ApiResponse(responseCode = "201", description = "Categories retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class)))
    @ApiResponse(responseCode = "404", description = "No data found for categories", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/")
    public ResponseEntity<PaginatedResult<CategoryResponse>> getAllCategories(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort){
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page, size, sort)));
    }
}
