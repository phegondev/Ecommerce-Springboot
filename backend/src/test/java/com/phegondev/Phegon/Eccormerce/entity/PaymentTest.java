package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        Order mockOrder1 = mock(Order.class);  // Mock first order
        Order mockOrder2 = mock(Order.class);  // Mock second order

        // Payment 1
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(BigDecimal.valueOf(100));
        payment1.setMethod("Credit Card");
        payment1.setStatus("Paid");
        payment1.setOrder(mockOrder1);

        // Payment 2 (same as Payment 1)
        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setAmount(BigDecimal.valueOf(100));
        payment2.setMethod("Credit Card");
        payment2.setStatus("Paid");
        payment2.setOrder(mockOrder1);

        // Payment 3 (different fields)
        Payment payment3 = new Payment();
        payment3.setId(2L);
        payment3.setAmount(BigDecimal.valueOf(150));
        payment3.setMethod("Debit Card");
        payment3.setStatus("Pending");
        payment3.setOrder(mockOrder2);

        // Act & Assert
        // Same object, should return true
        assertTrue(payment1.equals(payment1));

        // Null comparison, should return false
        assertFalse(payment1.equals(null));

        // Different class comparison, should return false
        assertFalse(payment1.equals(new Object()));

        // Same id, amount, method, status, order, should return true
        assertTrue(payment1.equals(payment2));

        // Different id, should return false
        assertFalse(payment1.equals(payment3));

        // Different amount, should return false
        payment3.setAmount(BigDecimal.valueOf(200));
        assertFalse(payment1.equals(payment3));

        // Different method, should return false
        payment3.setAmount(BigDecimal.valueOf(100));
        payment3.setMethod("PayPal");
        assertFalse(payment1.equals(payment3));

        // Different status, should return false
        payment3.setMethod("Credit Card");
        payment3.setStatus("Cancelled");
        assertFalse(payment1.equals(payment3));

        // Different order, should return false
        payment3.setStatus("Paid");
        payment3.setOrder(mockOrder2);
        assertFalse(payment1.equals(payment3));

        // Test canEqual method
        assertTrue(payment1.canEqual(payment2));  // Same type, should return true
        assertFalse(payment1.canEqual(new Object()));  // Different type, should return false
    }

    @Test
    void testHashCode() {
        // Arrange
        Order mockOrder1 = mock(Order.class);  // Mock first order
        Order mockOrder2 = mock(Order.class);  // Mock second order

        // Payment 1
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(BigDecimal.valueOf(100));
        payment1.setMethod("Credit Card");
        payment1.setStatus("Paid");
        payment1.setOrder(mockOrder1);

        // Payment 2 (same as Payment 1)
        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setAmount(BigDecimal.valueOf(100));
        payment2.setMethod("Credit Card");
        payment2.setStatus("Paid");
        payment2.setOrder(mockOrder1);

        // Payment 3 (different id)
        Payment payment3 = new Payment();
        payment3.setId(2L);
        payment3.setAmount(BigDecimal.valueOf(100));
        payment3.setMethod("Credit Card");
        payment3.setStatus("Paid");
        payment3.setOrder(mockOrder1);

        // Payment 4 (different order)
        Payment payment4 = new Payment();
        payment4.setId(1L);
        payment4.setAmount(BigDecimal.valueOf(100));
        payment4.setMethod("Credit Card");
        payment4.setStatus("Paid");
        payment4.setOrder(mockOrder2);

        // Act & Assert
        // Ensure payments with the same attributes return the same hash code
        assertEquals(payment1.hashCode(), payment2.hashCode());

        // Ensure payments with different attributes return different hash codes
        assertNotEquals(payment1.hashCode(), payment3.hashCode());
        assertNotEquals(payment1.hashCode(), payment4.hashCode());
    }

    @Test
    void testEqualsWithNullFields() {
        // Test with some null values to ensure they don't break equality.
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(null);
        payment1.setMethod(null);
        payment1.setStatus(null);
        payment1.setOrder(null);

        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setAmount(null);
        payment2.setMethod(null);
        payment2.setStatus(null);
        payment2.setOrder(null);

        // Both payments are the same, should return true
        assertTrue(payment1.equals(payment2));
    }

    @Test
    void testHashCodeWithNullFields() {
        // Test with some null fields to ensure hashCode works.
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(null);
        payment1.setMethod(null);
        payment1.setStatus(null);
        payment1.setOrder(null);

        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setAmount(null);
        payment2.setMethod(null);
        payment2.setStatus(null);
        payment2.setOrder(null);

        // Both payments are the same, should return the same hashCode
        assertEquals(payment1.hashCode(), payment2.hashCode());
    }
}
