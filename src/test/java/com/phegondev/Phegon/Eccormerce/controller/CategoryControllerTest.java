package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.CategoryDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.service.interf.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    void testCreateCategory_Success() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Electronics");

        Response mockResponse = Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();

        when(categoryService.createCategory(categoryDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = categoryController.createCategory(categoryDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(categoryService, times(1)).createCategory(categoryDto);
    }

    @Test
    void testGetAllCategories_Success() {
        // Arrange
        Response mockResponse = Response.builder()
                .status(200)
                .message("All categories fetched successfully")
                .build();

        when(categoryService.getAllCategories()).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = categoryController.getAllCategories();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void testUpdateCategory_Success() {
        // Arrange
        Long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Updated Electronics");

        Response mockResponse = Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();

        when(categoryService.updateCategory(categoryId, categoryDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = categoryController.updateCategory(categoryId, categoryDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(categoryService, times(1)).updateCategory(categoryId, categoryDto);
    }

    @Test
    void testDeleteCategory_Success() {
        // Arrange
        Long categoryId = 1L;

        Response mockResponse = Response.builder()
                .status(200)
                .message("Category was deleted successfully")
                .build();

        when(categoryService.deleteCategory(categoryId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = categoryController.deleteCategory(categoryId);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }

    @Test
    void testGetCategoryById_Success() {
        // Arrange
        Long categoryId = 1L;

        Response mockResponse = Response.builder()
                .status(200)
                .message("Category fetched successfully")
                .build();

        when(categoryService.getCategoryById(categoryId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = categoryController.getCategoryById(categoryId);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(categoryService, times(1)).getCategoryById(categoryId);
    }
}
