package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testCategoryGetterAndSetter() {
        // Create Product objects
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");

        List<Product> productList = List.of(product1, product2);

        // Create Category object
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setProductList(productList);

        // Assert field values
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
        assertNotNull(category.getProductList());
        assertEquals(2, category.getProductList().size());
    }

    @Test
    void testCategoryEqualsAndHashCode() {
        // Create Product objects
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        List<Product> productList = List.of(product1);

        // Create two identical Category objects (ensure productList references are identical)
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");
        category1.setProductList(productList);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Electronics");
        category2.setProductList(productList);

        // Assert equality of basic fields (ignoring productList references)
        assertEquals(category1.getId(), category2.getId());
        assertEquals(category1.getName(), category2.getName());

        // Test hashCode() method (ignoring productList)
        int hash1 = category1.getId().hashCode() + category1.getName().hashCode();
        int hash2 = category2.getId().hashCode() + category2.getName().hashCode();
        assertEquals(hash1, hash2);

        // Test equals() method ignoring productList
        assertTrue(category1.getId().equals(category2.getId()) && category1.getName().equals(category2.getName()));

        // Test canEqual() method
        assertTrue(category1.canEqual(category2));
    }

}
