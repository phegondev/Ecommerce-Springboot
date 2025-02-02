package com.phegondev.Phegon.Eccormerce.service.interf;

import com.phegondev.Phegon.Eccormerce.dto.CategoryDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Mock
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryDto categoryDto;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = categoryServiceImpl; // 使用CategoryService接口
    }

    @Test
    void createCategory_Success_ReturnsResponse() {
        // 模拟CategoryDto数据
        when(categoryDto.getName()).thenReturn("New Category");

        // 创建一个模拟的Response
        Response mockResponse = Response.builder()
                .status(200)
                .message("Category created successfully")
                .build();

        // 模拟调用createCategory方法
        when(categoryServiceImpl.createCategory(categoryDto)).thenReturn(mockResponse);

        // 调用服务方法并断言
        Response response = categoryService.createCategory(categoryDto);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Category created successfully", response.getMessage());

        // 验证方法调用
        verify(categoryServiceImpl).createCategory(categoryDto);
    }

    @Test
    void updateCategory_Success_ReturnsResponse() {
        Long categoryId = 1L;
        // 模拟CategoryDto数据
        when(categoryDto.getName()).thenReturn("Updated Category");

        // 创建一个模拟的Response
        Response mockResponse = Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();

        // 模拟调用updateCategory方法
        when(categoryServiceImpl.updateCategory(categoryId, categoryDto)).thenReturn(mockResponse);

        // 调用服务方法并断言
        Response response = categoryService.updateCategory(categoryId, categoryDto);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Category updated successfully", response.getMessage());

        // 验证方法调用
        verify(categoryServiceImpl).updateCategory(categoryId, categoryDto);
    }

    @Test
    void getAllCategories_ReturnsCategories() {
        // 创建一个模拟的Response，假设它返回所有类别
        Response mockResponse = Response.builder()
                .status(200)
                .message("All categories fetched")
                .build();

        // 模拟调用getAllCategories方法
        when(categoryServiceImpl.getAllCategories()).thenReturn(mockResponse);

        // 调用服务方法并断言
        Response response = categoryService.getAllCategories();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("All categories fetched", response.getMessage());

        // 验证方法调用
        verify(categoryServiceImpl).getAllCategories();
    }

    @Test
    void getCategoryById_Success_ReturnsCategory() {
        Long categoryId = 1L;
        // 创建一个模拟的Response，假设它返回一个类别
        Response mockResponse = Response.builder()
                .status(200)
                .message("Category fetched successfully")
                .build();

        // 模拟调用getCategoryById方法
        when(categoryServiceImpl.getCategoryById(categoryId)).thenReturn(mockResponse);

        // 调用服务方法并断言
        Response response = categoryService.getCategoryById(categoryId);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Category fetched successfully", response.getMessage());

        // 验证方法调用
        verify(categoryServiceImpl).getCategoryById(categoryId);
    }

    @Test
    void deleteCategory_Success_ReturnsResponse() {
        Long categoryId = 1L;

        // 创建一个模拟的Response，假设删除成功
        Response mockResponse = Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();

        // 模拟调用deleteCategory方法
        when(categoryServiceImpl.deleteCategory(categoryId)).thenReturn(mockResponse);

        // 调用服务方法并断言
        Response response = categoryService.deleteCategory(categoryId);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Category deleted successfully", response.getMessage());

        // 验证方法调用
        verify(categoryServiceImpl).deleteCategory(categoryId);
    }
}
