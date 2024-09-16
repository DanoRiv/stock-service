package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.ItemRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ItemRequestMapper;
import com.pragma_bootcamp.stock_service.configuration.exceptionhandler.ExceptionResponse;
import com.pragma_bootcamp.stock_service.domain.api.IItemServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemControllerAdapter {
    private final IItemServicePort itemServicePort;
    private final ItemRequestMapper itemRequestMapper;

    @Operation(summary = "Create new item")
        @ApiResponse(responseCode = "201", description = "Item created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemRequest.class)))
        @ApiResponse(responseCode = "400", description = "Couldn't create Item", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping("/")
    public ResponseEntity<Void> addItem(@Valid @RequestBody ItemRequest itemRequest){
        itemServicePort.saveItem(itemRequestMapper.toItem(itemRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
