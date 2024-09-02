package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.BrandRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.response.BrandResponse;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_bootcamp.stock_service.domain.api.IBrandServicePort;
import com.pragma_bootcamp.stock_service.domain.util.PaginatedResult;
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

    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@Valid @RequestBody BrandRequest brandRequest){
        brandServicePort.saveBrand(brandRequestMapper.toBrand(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<PaginatedResult<BrandResponse>> getAllBrands(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort ){
        return ResponseEntity.ok(brandResponseMapper.toBrandResponseList(brandServicePort.getAllBrands(page, size, sort)));
    }
}
