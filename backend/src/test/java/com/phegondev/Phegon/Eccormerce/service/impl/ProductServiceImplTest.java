package com.phegondev.Phegon.Eccormerce.service.impl;

import com.phegondev.Phegon.Eccormerce.dto.ProductDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.entity.Category;
import com.phegondev.Phegon.Eccormerce.entity.Product;
import com.phegondev.Phegon.Eccormerce.exception.NotFoundException;
import com.phegondev.Phegon.Eccormerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon.Eccormerce.repository.CategoryRepo;
import com.phegondev.Phegon.Eccormerce.repository.ProductRepo;
import com.phegondev.Phegon.Eccormerce.service.AwsS3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private AwsS3Service awsS3Service;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductSuccess() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(awsS3Service.saveImageToS3(multipartFile)).thenReturn("http://example.com/image.jpg");

        // Act
        Response response = productService.createProduct(
                categoryId, multipartFile, "Product Name", "Description", BigDecimal.valueOf(100)
        );

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Product successfully created", response.getMessage());
        verify(productRepo, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProductCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.createProduct(
                categoryId, multipartFile, "Product Name", "Description", BigDecimal.valueOf(100)
        ));
    }

    @Test
    void testUpdateProductSuccess() {
        // Arrange
        Long productId = 1L;
        Long categoryId = 2L;
        Product product = new Product();
        product.setId(productId);

        Category category = new Category();
        category.setId(categoryId);

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(awsS3Service.saveImageToS3(multipartFile)).thenReturn("http://example.com/image.jpg");

        // Act
        Response response = productService.updateProduct(
                productId, categoryId, multipartFile, "Updated Name", "Updated Description", BigDecimal.valueOf(200)
        );

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Product updated successfully", response.getMessage());
        verify(productRepo, times(1)).save(product);
    }

    @Test
    void testUpdateProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.updateProduct(
                productId, null, null, "Name", "Description", BigDecimal.valueOf(100)
        ));
    }

    @Test
    void testDeleteProductSuccess() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Response response = productService.deleteProduct(productId);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Product deleted successfully", response.getMessage());
        verify(productRepo, times(1)).delete(product);
    }

    @Test
    void testDeleteProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.deleteProduct(productId));
    }

    @Test
    void testGetProductByIdSuccess() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        ProductDto productDto = new ProductDto();

        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(entityDtoMapper.mapProductToDtoBasic(product)).thenReturn(productDto);

        // Act
        Response response = productService.getProductById(productId);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(productDto, response.getProduct());
    }

    @Test
    void testGetProductByIdNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepo.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        ProductDto productDto = new ProductDto();

        when(productRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(Collections.singletonList(product));
        when(entityDtoMapper.mapProductToDtoBasic(product)).thenReturn(productDto);

        // Act
        Response response = productService.getAllProducts();

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getProductList());
        assertEquals(1, response.getProductList().size());
    }

    @Test
    void testGetProductsByCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        Product product = new Product();
        product.setId(1L);
        ProductDto productDto = new ProductDto();

        when(productRepo.findByCategoryId(categoryId)).thenReturn(Collections.singletonList(product));
        when(entityDtoMapper.mapProductToDtoBasic(product)).thenReturn(productDto);

        // Act
        Response response = productService.getProductsByCategory(categoryId);

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getProductList());
        assertEquals(1, response.getProductList().size());
    }

    @Test
    void testGetProductsByCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(productRepo.findByCategoryId(categoryId)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.getProductsByCategory(categoryId));
    }

    @Test
    void testSearchProductSuccess() {
        // Arrange
        String searchValue = "Product";
        Product product = new Product();
        product.setId(1L);
        ProductDto productDto = new ProductDto();

        when(productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue))
                .thenReturn(Collections.singletonList(product));
        when(entityDtoMapper.mapProductToDtoBasic(product)).thenReturn(productDto);

        // Act
        Response response = productService.searchProduct(searchValue);

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getProductList());
        assertEquals(1, response.getProductList().size());
    }

    @Test
    void testSearchProductNotFound() {
        // Arrange
        String searchValue = "Product";
        when(productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> productService.searchProduct(searchValue));
    }
}
