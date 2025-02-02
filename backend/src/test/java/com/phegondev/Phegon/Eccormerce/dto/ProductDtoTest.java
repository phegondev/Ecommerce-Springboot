package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {

    @Test
    void testProductDtoGetterAndSetter() {
        // 创建 CategoryDto 对象
        CategoryDto category = new CategoryDto(1L, "Electronics", null);

        // 创建 ProductDto 对象
        ProductDto product = new ProductDto();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop with high performance");
        product.setPrice(BigDecimal.valueOf(1299.99));
        product.setImageUrl("laptop.jpg");
        product.setCategory(category);

        // 断言字段正确
        assertEquals(1L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("Gaming Laptop with high performance", product.getDescription());
        assertEquals(BigDecimal.valueOf(1299.99), product.getPrice());
        assertEquals("laptop.jpg", product.getImageUrl());
        assertNotNull(product.getCategory());
        assertEquals("Electronics", product.getCategory().getName());
    }

    @Test
    void testProductDtoEqualsAndHashCode() {
        // 创建 CategoryDto 对象
        CategoryDto category = new CategoryDto(1L, "Electronics", null);

        // 创建两个相同的 ProductDto 对象
        ProductDto product1 = new ProductDto(1L, "Laptop", "Gaming Laptop", BigDecimal.valueOf(1299.99), "laptop.jpg", category);
        ProductDto product2 = new ProductDto(1L, "Laptop", "Gaming Laptop", BigDecimal.valueOf(1299.99), "laptop.jpg", category);

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testProductDtoToString() {
        ProductDto product = new ProductDto(1L, "Laptop", "Gaming Laptop", BigDecimal.valueOf(1299.99), "laptop.jpg", null);

        assertNotNull(product.toString());
        assertTrue(product.toString().contains("Laptop"));
        assertTrue(product.toString().contains("1299.99"));
    }
}
