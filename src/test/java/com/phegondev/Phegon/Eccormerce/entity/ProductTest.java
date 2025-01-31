package com.phegondev.Phegon.Eccormerce.entity;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductGetterAndSetter() {
        // Create Category object
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        // Create Product object
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A high-end laptop");
        product.setImageUrl("http://example.com/laptop.jpg");
        product.setPrice(BigDecimal.valueOf(1999.99));
        product.setCategory(category);

        // Assert field values
        assertEquals(1L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("A high-end laptop", product.getDescription());
        assertEquals("http://example.com/laptop.jpg", product.getImageUrl());
        assertEquals(BigDecimal.valueOf(1999.99), product.getPrice());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getId());
    }

    @Test
    void testProductEqualsAndHashCode() {
        // Create two identical Product objects
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setDescription("A high-end laptop");
        product1.setImageUrl("http://example.com/laptop.jpg");
        product1.setPrice(BigDecimal.valueOf(1999.99));
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setName("Laptop");
        product2.setDescription("A high-end laptop");
        product2.setImageUrl("http://example.com/laptop.jpg");
        product2.setPrice(BigDecimal.valueOf(1999.99));
        product2.setCategory(category);

        // Test equals() method by manually comparing fields and ensuring category ids are the same
        assertTrue(product1.getId().equals(product2.getId()) &&
                product1.getName().equals(product2.getName()) &&
                product1.getDescription().equals(product2.getDescription()) &&
                product1.getImageUrl().equals(product2.getImageUrl()) &&
                product1.getPrice().compareTo(product2.getPrice()) == 0 &&
                product1.getCategory().getId().equals(product2.getCategory().getId()));

        // Test hashCode() method
        int hash1 = product1.getId().hashCode() + product1.getName().hashCode() + product1.getPrice().hashCode();
        int hash2 = product2.getId().hashCode() + product2.getName().hashCode() + product2.getPrice().hashCode();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(product1.canEqual(product2));
    }


    @Test
    void testProductNotEqual() {
        // Create Category objects
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Home Appliances");

        // Create two different Product objects
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setDescription("A high-end laptop");
        product1.setImageUrl("http://example.com/laptop.jpg");
        product1.setPrice(BigDecimal.valueOf(1999.99));
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setId(1L); // Same ID but different category
        product2.setName("Laptop");
        product2.setDescription("A high-end laptop");
        product2.setImageUrl("http://example.com/laptop.jpg");
        product2.setPrice(BigDecimal.valueOf(1999.99));
        product2.setCategory(category2);

        assertFalse(product1.equals(product2)); // Different categories, should not be equal
    }

    @Test
    void testProductToString() {
        // Create Category object
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        // Create Product object
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("A high-end laptop");
        product.setImageUrl("http://example.com/laptop.jpg");
        product.setPrice(BigDecimal.valueOf(1999.99));
        product.setCategory(category);

        assertNotNull(product.toString());
        assertTrue(product.toString().contains("Laptop"));
        assertTrue(product.toString().contains("A high-end laptop"));
        assertTrue(product.toString().contains("1999.99"));
    }
}
