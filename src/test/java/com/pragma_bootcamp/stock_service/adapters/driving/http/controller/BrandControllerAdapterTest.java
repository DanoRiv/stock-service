package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.google.gson.Gson;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.BrandRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_bootcamp.stock_service.domain.api.IBrandServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BrandControllerAdapter.class)
class BrandControllerAdapterTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IBrandServicePort brandServicePort;
    @MockBean
    private IBrandRequestMapper brandRequestMapper;
    @MockBean
    private IBrandResponseMapper brandResponseMapper;
    Gson gson = new Gson();

    @Test
    void shouldAddBrandCorrectly() throws Exception {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Mazda");
        brandRequest.setDescription("Cars Brand");


        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(brandRequest)))
                .andExpect(status().isCreated());

        verify(brandServicePort, times(1)).saveBrand(brandRequestMapper.toBrand(brandRequest));
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong () throws Exception{
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("This name should throw an exception since it exceeds 50 characters");
        brandRequest.setDescription("Cars Brand");

        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(brandRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooLong () throws Exception{
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Home");
        brandRequest.setDescription("This is a very long description and it violates the constraint of the max 120 characters for the description so it should throw an exception");

        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(brandRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAllBrands() throws Exception {
        int page = 0;
        int size = 2;
        String sort = "asc";

        mockMvc.perform(get("/brand/")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        verify(brandServicePort, times(1)).getAllBrands(page, size, sort);
    }
}