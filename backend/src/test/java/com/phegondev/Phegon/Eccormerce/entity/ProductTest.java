package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        Category mockCategory = mock(Category.class);  // Mock Category

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setImageUrl("http://example.com/product1.jpg");
        product1.setPrice(BigDecimal.valueOf(100));
        product1.setCategory(mockCategory);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Product 1");
        product2.setDescription("Description 1");
        product2.setImageUrl("http://example.com/product1.jpg");
        product2.setPrice(BigDecimal.valueOf(100));
        product2.setCategory(mockCategory);

        Product product3 = new Product();
        product3.setId(2L);
        product3.setName("Product 2");
        product3.setDescription("Description 2");
        product3.setImageUrl("http://example.com/product2.jpg");
        product3.setPrice(BigDecimal.valueOf(150));
        product3.setCategory(mockCategory);

        // Act & Assert
        // Same object, should return true
        assertTrue(product1.equals(product1));

        // Null comparison, should return false
        assertFalse(product1.equals(null));

        // Different class comparison, should return false
        assertFalse(product1.equals(new Object()));

        // Same id, name, description, imageUrl, price, category, should return true
        assertTrue(product1.equals(product2));

        // Different id, should return false
        assertFalse(product1.equals(product3));

        // Test canEqual method
        assertTrue(product1.canEqual(product2));  // Same type, should return true
        assertFalse(product1.canEqual(new Object()));  // Different type, should return false
    }

    @Test
    void testHashCode() {
        // Arrange
        Category mockCategory = mock(Category.class);  // Mock Category

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setImageUrl("http://example.com/product1.jpg");
        product1.setPrice(BigDecimal.valueOf(100));
        product1.setCategory(mockCategory);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Product 1");
        product2.setDescription("Description 1");
        product2.setImageUrl("http://example.com/product1.jpg");
        product2.setPrice(BigDecimal.valueOf(100));
        product2.setCategory(mockCategory);

        Product product3 = new Product();
        product3.setId(2L);
        product3.setName("Product 2");
        product3.setDescription("Description 2");
        product3.setImageUrl("http://example.com/product2.jpg");
        product3.setPrice(BigDecimal.valueOf(150));
        product3.setCategory(mockCategory);

        // Act & Assert
        // Ensure products with the same attributes return the same hash code
        assertEquals(product1.hashCode(), product2.hashCode());

        // Ensure products with different attributes return different hash codes
        assertNotEquals(product1.hashCode(), product3.hashCode());
    }
}
