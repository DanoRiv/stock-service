package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.BrandRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.CategoryRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.BrandResponse;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_bootcamp.stock_service.configuration.exceptionhandler.ExceptionResponse;
import com.pragma_bootcamp.stock_service.domain.api.IBrandServicePort;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandControllerAdapter {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Operation(summary = "Create new brand")
    @ApiResponse(responseCode = "201", description = "Brand created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BrandRequest.class)))
    @ApiResponse(responseCode = "400", description = "Couldn't create brand", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@Valid @RequestBody BrandRequest brandRequest){
        brandServicePort.saveBrand(brandRequestMapper.toBrand(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get a paginated list of brands")
    @ApiResponse(responseCode = "201", description = "Brands retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class)))
    @ApiResponse(responseCode = "404", description = "No data found for brand", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/")
    public ResponseEntity<PaginatedResult<BrandResponse>> getAllBrands(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort ){
        return ResponseEntity.ok(brandResponseMapper.toBrandResponseList(brandServicePort.getAllBrands(page, size, sort)));
    }
}
