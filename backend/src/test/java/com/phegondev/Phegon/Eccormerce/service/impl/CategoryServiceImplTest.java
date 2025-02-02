package com.phegondev.Phegon.Eccormerce.service.impl;

import com.phegondev.Phegon.Eccormerce.dto.CategoryDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.entity.Category;
import com.phegondev.Phegon.Eccormerce.exception.NotFoundException;
import com.phegondev.Phegon.Eccormerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon.Eccormerce.repository.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Electronics");

        // Act
        Response response = categoryService.createCategory(categoryDto);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Category created successfully", response.getMessage());
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    @Test
    void testUpdateCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Electronics");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Updated Electronics");

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Response response = categoryService.updateCategory(categoryId, categoryDto);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("category updated successfully", response.getMessage());
        assertEquals("Updated Electronics", category.getName());
        verify(categoryRepo, times(1)).save(category);
    }

    @Test
    void testUpdateCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Updated Electronics");

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> categoryService.updateCategory(categoryId, categoryDto));
    }

    @Test
    void testGetAllCategories() {
        // Arrange
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Books");

        when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2));
        when(entityDtoMapper.mapCategoryToDtoBasic(category1)).thenReturn(new CategoryDto());
        when(entityDtoMapper.mapCategoryToDtoBasic(category2)).thenReturn(new CategoryDto());

        // Act
        Response response = categoryService.getAllCategories();

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getCategoryList());
        assertEquals(2, response.getCategoryList().size());
    }

    @Test
    void testGetCategoryByIdSuccess() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Electronics");

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(entityDtoMapper.mapCategoryToDtoBasic(category)).thenReturn(new CategoryDto());

        // Act
        Response response = categoryService.getCategoryById(categoryId);

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getCategory());
    }

    @Test
    void testGetCategoryByIdNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> categoryService.getCategoryById(categoryId));
    }

    @Test
    void testDeleteCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Response response = categoryService.deleteCategory(categoryId);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Category was deleted successfully", response.getMessage());
        verify(categoryRepo, times(1)).delete(category);
    }

    @Test
    void testDeleteCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> categoryService.deleteCategory(categoryId));
    }
}
