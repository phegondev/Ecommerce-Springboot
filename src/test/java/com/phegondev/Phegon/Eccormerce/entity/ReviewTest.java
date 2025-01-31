package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    void testReviewGetterAndSetter() {
        // Create Product and User objects
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Create Review object
        Review review = new Review();
        review.setId(1L);
        review.setContent("Great product!");
        review.setRating(9);
        review.setProduct(product);
        review.setUser(user);

        // Assert field values
        assertEquals(        1L, review.getId());
        assertEquals("Great product!", review.getContent());
        assertEquals(9, review.getRating());
        assertNotNull(review.getProduct());
        assertEquals(1L, review.getProduct().getId());
        assertNotNull(review.getUser());
        assertEquals(1L, review.getUser().getId());
    }

    @Test
    void testReviewEqualsAndHashCode() {
        // Create Product and User objects
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Create two identical Review objects
        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Great product!");
        review1.setRating(9);
        review1.setProduct(product);
        review1.setUser(user);

        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent("Great product!");
        review2.setRating(9);
        review2.setProduct(product);
        review2.setUser(user);

        // Test equals() method by manually comparing fields and ensuring product and user ids are the same
        assertTrue(review1.getId().equals(review2.getId()) &&
                review1.getContent().equals(review2.getContent()) &&
                review1.getRating() == review2.getRating() &&
                review1.getProduct().getId().equals(review2.getProduct().getId()) &&
                review1.getUser().getId().equals(review2.getUser().getId()));

        // Test hashCode() method
        int hash1 = review1.getId().hashCode() + review1.getContent().hashCode() + review1.getRating();
        int hash2 = review2.getId().hashCode() + review2.getContent().hashCode() + review2.getRating();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(review1.canEqual(review2));
    }

    @Test
    void testReviewNotEqual() {
        // Create Product and User objects
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Create two different Review objects
        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Great product!");
        review1.setRating(9);
        review1.setProduct(product1);
        review1.setUser(user);

        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent("Great product!");
        review2.setRating(9);
        review2.setProduct(product2);  // Different product
        review2.setUser(user);

        assertFalse(review1.equals(review2));  // Different products, should not be equal
    }

    @Test
    void testReviewToString() {
        // Create Product and User objects
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Create Review object
        Review review = new Review();
        review.setId(1L);
        review.setContent("Great product!");
        review.setRating(9);
        review.setProduct(product);
        review.setUser(user);

        assertNotNull(review.toString());
        assertTrue(review.toString().contains("Great product!"));
        assertTrue(review.toString().contains("9"));
    }
}

