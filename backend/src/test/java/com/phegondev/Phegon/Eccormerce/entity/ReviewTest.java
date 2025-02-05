package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ReviewTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        Product mockProduct1 = mock(Product.class);  // Mock Product
        Product mockProduct2 = mock(Product.class);  // Mock another Product
        User mockUser1 = mock(User.class);  // Mock User
        User mockUser2 = mock(User.class);  // Mock another User

        // Review 1
        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Great product!");
        review1.setRating(8);
        review1.setProduct(mockProduct1);
        review1.setUser(mockUser1);

        // Review 2 (same as Review 1)
        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent("Great product!");
        review2.setRating(8);
        review2.setProduct(mockProduct1);
        review2.setUser(mockUser1);

        // Review 3 (different fields)
        Review review3 = new Review();
        review3.setId(2L);
        review3.setContent("Not bad.");
        review3.setRating(5);
        review3.setProduct(mockProduct2);
        review3.setUser(mockUser2);

        // Act & Assert
        // Same object, should return true
        assertTrue(review1.equals(review1));

        // Null comparison, should return false
        assertFalse(review1.equals(null));

        // Different class comparison, should return false
        assertFalse(review1.equals(new Object()));

        // Same id, content, rating, product, user, should return true
        assertTrue(review1.equals(review2));

        // Different id, should return false
        assertFalse(review1.equals(review3));

        // Different content, should return false
        review3.setContent("Awful product.");
        assertFalse(review1.equals(review3));

        // Different rating, should return false
        review3.setContent("Not bad.");
        review3.setRating(2);
        assertFalse(review1.equals(review3));

        // Different product, should return false
        review3.setRating(8);
        review3.setProduct(mockProduct1);
        review3.setUser(mockUser2); // Different user
        assertFalse(review1.equals(review3));

        // Different user, should return false
        review3.setUser(mockUser1); // Same user
        review3.setProduct(mockProduct2); // Different product
        assertFalse(review1.equals(review3));

        // Test canEqual method
        assertTrue(review1.canEqual(review2));  // Same type, should return true
        assertFalse(review1.canEqual(new Object()));  // Different type, should return false
    }

    @Test
    void testHashCode() {
        // Arrange
        Product mockProduct1 = mock(Product.class);  // Mock Product
        Product mockProduct2 = mock(Product.class);  // Mock another Product
        User mockUser1 = mock(User.class);  // Mock User

        // Review 1
        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Great product!");
        review1.setRating(8);
        review1.setProduct(mockProduct1);
        review1.setUser(mockUser1);

        // Review 2 (same as Review 1)
        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent("Great product!");
        review2.setRating(8);
        review2.setProduct(mockProduct1);
        review2.setUser(mockUser1);

        // Review 3 (different id)
        Review review3 = new Review();
        review3.setId(2L);
        review3.setContent("Not bad.");
        review3.setRating(5);
        review3.setProduct(mockProduct2);
        review3.setUser(mockUser1);

        // Review 4 (different product)
        Review review4 = new Review();
        review4.setId(1L);
        review4.setContent("Great product!");
        review4.setRating(8);
        review4.setProduct(mockProduct2);  // Different product
        review4.setUser(mockUser1);

        // Act & Assert
        // Ensure reviews with the same attributes return the same hash code
        assertEquals(review1.hashCode(), review2.hashCode());

        // Ensure reviews with different attributes return different hash codes
        assertNotEquals(review1.hashCode(), review3.hashCode());
        assertNotEquals(review1.hashCode(), review4.hashCode());
    }

    @Test
    void testHashCodeWithNullFields() {
        // Test with some null fields to ensure hashCode works.
        Product mockProduct = mock(Product.class);  // Mock Product
        User mockUser = mock(User.class);  // Mock User

        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent(null);  // Set content to null
        review1.setRating(8);
        review1.setProduct(mockProduct);
        review1.setUser(mockUser);

        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent(null);  // Set content to null
        review2.setRating(8);
        review2.setProduct(mockProduct);
        review2.setUser(mockUser);

        // Ensure reviews with the same attributes (including null content) return the same hash code
        assertEquals(review1.hashCode(), review2.hashCode());
    }

    @Test
    void testEqualsWithNullFields() {
        // Test with some null values to ensure equals works.
        Product mockProduct = mock(Product.class);  // Mock Product
        User mockUser = mock(User.class);  // Mock User

        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent(null);  // Set content to null
        review1.setRating(8);
        review1.setProduct(mockProduct);
        review1.setUser(mockUser);

        Review review2 = new Review();
        review2.setId(1L);
        review2.setContent(null);  // Set content to null
        review2.setRating(8);
        review2.setProduct(mockProduct);
        review2.setUser(mockUser);

        // Both reviews are the same, should return true
        assertTrue(review1.equals(review2));
    }
}
