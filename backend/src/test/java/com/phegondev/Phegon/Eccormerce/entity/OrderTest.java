package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        Order order1 = new Order();
        order1.setId(1L);
        order1.setTotalPrice(BigDecimal.valueOf(100));

        Order order2 = new Order();
        order2.setId(1L);
        order2.setTotalPrice(BigDecimal.valueOf(100));

        Order order3 = new Order();
        order3.setId(2L);
        order3.setTotalPrice(BigDecimal.valueOf(200));

        Order order4 = new Order();
        order4.setId(1L);
        order4.setTotalPrice(BigDecimal.valueOf(100));

        // Act & Assert
        // Same object, should return true
        assertTrue(order1.equals(order1));

        // Null comparison, should return false
        assertFalse(order1.equals(null));

        // Different class comparison, should return false
        assertFalse(order1.equals(new Object()));

        // Same id and same totalPrice, should return true
        assertTrue(order1.equals(order2));

        // Different id, should return false
        assertFalse(order1.equals(order3));

        // Same id and totalPrice, should return true
        assertTrue(order1.equals(order4));

        // Test canEqual method
        assertTrue(order1.canEqual(order2));  // Same type, should return true
        assertFalse(order1.canEqual(new Object()));  // Different type, should return false
    }

    @Test
    void testHashCode() {
        // Arrange
        Order order1 = new Order();
        order1.setId(1L);
        order1.setTotalPrice(BigDecimal.valueOf(100));

        Order order2 = new Order();
        order2.setId(1L);
        order2.setTotalPrice(BigDecimal.valueOf(100));

        Order order3 = new Order();
        order3.setId(2L);
        order3.setTotalPrice(BigDecimal.valueOf(200));

        // Act & Assert
        // Ensure categories with the same id return the same hash code
        assertEquals(order1.hashCode(), order2.hashCode());

        // Ensure categories with different id return different hash code
        assertNotEquals(order1.hashCode(), order3.hashCode());
    }

}
