package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDtoTest {

    @Test
    void testCategoryDtoGetterAndSetter() {
        // 创建 ProductDto 对象
        ProductDto product1 = new ProductDto(1L, "Product 1", "Description 1", BigDecimal.valueOf(10.99), "image1.jpg", null);
        ProductDto product2 = new ProductDto(2L, "Product 2", "Description 2", BigDecimal.valueOf(20.99), "image2.jpg", null);
        List<ProductDto> productList = Arrays.asList(product1, product2);

        // 创建 CategoryDto 对象
        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setName("Electronics");
        category.setProductList(productList);

        // 断言：确保字段正确
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
        assertNotNull(category.getProductList());
        assertEquals(2, category.getProductList().size());
        assertEquals("Product 1", category.getProductList().get(0).getName());
        assertEquals("Product 2", category.getProductList().get(1).getName());
    }

    @Test
    void testCategoryDtoEqualsAndHashCode() {
        // 创建相同的 ProductDto 列表
        ProductDto product1 = new ProductDto(1L, "Product 1", "Description 1", BigDecimal.valueOf(10.99), "image1.jpg", null);
        ProductDto product2 = new ProductDto(2L, "Product 2", "Description 2", BigDecimal.valueOf(20.99), "image2.jpg", null);
        List<ProductDto> productList = Arrays.asList(product1, product2);

        // 创建两个相同的 CategoryDto 对象
        CategoryDto category1 = new CategoryDto(1L, "Electronics", productList);
        CategoryDto category2 = new CategoryDto(1L, "Electronics", productList);

        // 断言：equals() 方法应该返回 true
        assertEquals(category1, category2);
        assertEquals(category1.hashCode(), category2.hashCode());
    }

    @Test
    void testCategoryDtoToString() {
        // 创建 ProductDto 列表
        ProductDto product1 = new ProductDto(1L, "Product 1", "Description 1", BigDecimal.valueOf(10.99), "image1.jpg", null);
        List<ProductDto> productList = List.of(product1);

        // 创建 CategoryDto 对象
        CategoryDto category = new CategoryDto(1L, "Electronics", productList);

        // toString() 不应该返回 null，并且应该包含类的关键信息
        assertNotNull(category.toString());
        assertTrue(category.toString().contains("Electronics"));
        assertTrue(category.toString().contains("Product 1"));
    }
}
