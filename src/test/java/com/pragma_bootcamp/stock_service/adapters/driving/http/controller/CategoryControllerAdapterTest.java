package com.pragma_bootcamp.stock_service.adapters.driving.http.controller;

import com.google.gson.Gson;
import com.pragma_bootcamp.stock_service.adapters.driving.http.dto.request.CategoryRequest;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_bootcamp.stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_bootcamp.stock_service.domain.api.ICategoryServicePort;
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

@WebMvcTest(controllers = CategoryControllerAdapter.class)
class CategoryControllerAdapterTest {
     @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICategoryServicePort categoryServicePort;
    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;
    @MockBean
    private ICategoryResponseMapper categoryResponseMapper;
    Gson gson = new Gson();

    @Test
    void shouldAddCategoryCorrectly() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Books");
        categoryRequest.setDescription("Books Category");


        mockMvc.perform(post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(categoryRequest)))
                .andExpect(status().isCreated());

        verify(categoryServicePort, times(1)).saveCategory(categoryRequestMapper.toCategory(categoryRequest));
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong () throws Exception{
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("This name should throw an exception since it exceeds 50 characters");
        categoryRequest.setDescription("Books Category");

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(categoryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsTooLong () throws Exception{
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Home");
        categoryRequest.setDescription("This is a veeery long description and it should throw an exception because it exceeds 90 characters");

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(categoryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        int page = 0;
        int size = 2;
        String sort = "asc";

        mockMvc.perform(get("/category/")
                    .param("page", String.valueOf(page))
                    .param("size", String.valueOf(size))
                    .param("sort", sort))
                .andExpect(status().isOk());

        verify(categoryServicePort, times(1)).getAllCategories(page, size, sort);
    }
}