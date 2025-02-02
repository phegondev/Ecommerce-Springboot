package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.exception.InvalidCredentialsException;
import com.phegondev.Phegon.Eccormerce.service.interf.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private MultipartFile mockImage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void testCreateProduct_Success() {
        // Arrange
        Long categoryId = 1L;
        String name = "Test Product";
        String description = "Test Description";
        BigDecimal price = new BigDecimal("100.00");

        when(mockImage.isEmpty()).thenReturn(false);
        Response mockResponse = Response.builder()
                .status(200)
                .message("Product created successfully")
                .build();
        when(productService.createProduct(categoryId, mockImage, name, description, price)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.createProduct(categoryId, mockImage, name, description, price);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product created successfully", response.getBody().getMessage());
        verify(productService, times(1)).createProduct(categoryId, mockImage, name, description, price);
    }

    @Test
    void testCreateProduct_InvalidInput() {
        // Arrange
        Long categoryId = null;
        String name = "";
        String description = "";
        BigDecimal price = null;

        when(mockImage.isEmpty()).thenReturn(true);

        // Act & Assert
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> productController.createProduct(categoryId, mockImage, name, description, price)
        );
        assertEquals("All Fields are Required", exception.getMessage());
        verify(productService, times(0)).createProduct(any(), any(), any(), any(), any());
    }

    @Test
    void testUpdateProduct_Success() {
        // Arrange
        Long productId = 1L;
        Long categoryId = 2L;
        String name = "Updated Product";
        String description = "Updated Description";
        BigDecimal price = new BigDecimal("150.00");

        Response mockResponse = Response.builder()
                .status(200)
                .message("Product updated successfully")
                .build();
        when(productService.updateProduct(productId, categoryId, mockImage, name, description, price)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.updateProduct(productId, categoryId, mockImage, name, description, price);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product updated successfully", response.getBody().getMessage());
        verify(productService, times(1)).updateProduct(productId, categoryId, mockImage, name, description, price);
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        Long productId = 1L;
        Response mockResponse = Response.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();
        when(productService.deleteProduct(productId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product deleted successfully", response.getBody().getMessage());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testGetProductById_Success() {
        // Arrange
        Long productId = 1L;
        Response mockResponse = Response.builder()
                .status(200)
                .message("Product retrieved successfully")
                .build();
        when(productService.getProductById(productId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.getProductById(productId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product retrieved successfully", response.getBody().getMessage());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testGetAllProducts_Success() {
        // Arrange
        Response mockResponse = Response.builder()
                .status(200)
                .message("All products retrieved successfully")
                .build();
        when(productService.getAllProducts()).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.getAllProducts();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("All products retrieved successfully", response.getBody().getMessage());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductsByCategory_Success() {
        // Arrange
        Long categoryId = 1L;
        Response mockResponse = Response.builder()
                .status(200)
                .message("Products retrieved by category successfully")
                .build();
        when(productService.getProductsByCategory(categoryId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.getProductsByCategory(categoryId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Products retrieved by category successfully", response.getBody().getMessage());
        verify(productService, times(1)).getProductsByCategory(categoryId);
    }

    @Test
    void testSearchForProduct_Success() {
        // Arrange
        String searchValue = "Product";
        Response mockResponse = Response.builder()
                .status(200)
                .message("Products found successfully")
                .build();
        when(productService.searchProduct(searchValue)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = productController.searchForProduct(searchValue);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Products found successfully", response.getBody().getMessage());
        verify(productService, times(1)).searchProduct(searchValue);
    }
}
