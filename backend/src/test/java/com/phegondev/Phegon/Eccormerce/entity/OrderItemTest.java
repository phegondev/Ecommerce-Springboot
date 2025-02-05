package com.phegondev.Phegon.Eccormerce.entity;

import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OrderItemTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        User mockUser = mock(User.class); // Mocking User
        Product mockProduct = mock(Product.class); // Mocking Product
        Order mockOrder = mock(Order.class); // Mocking Order

        // OrderItem 1
        OrderItem item1 = new OrderItem();
        item1.setId(1L);
        item1.setQuantity(2);
        item1.setPrice(BigDecimal.valueOf(100));
        item1.setStatus(OrderStatus.PENDING);
        item1.setUser(mockUser);
        item1.setProduct(mockProduct);
        item1.setOrder(mockOrder);

        // OrderItem 2 (identical to item1)
        OrderItem item2 = new OrderItem();
        item2.setId(1L);
        item2.setQuantity(2);
        item2.setPrice(BigDecimal.valueOf(100));
        item2.setStatus(OrderStatus.PENDING);
        item2.setUser(mockUser);
        item2.setProduct(mockProduct);
        item2.setOrder(mockOrder);

        // OrderItem 3 (different id, quantity, price, and status)
        OrderItem item3 = new OrderItem();
        item3.setId(2L);
        item3.setQuantity(3);
        item3.setPrice(BigDecimal.valueOf(150));
        item3.setStatus(OrderStatus.CANCELLED);
        item3.setUser(mockUser);
        item3.setProduct(mockProduct);
        item3.setOrder(mockOrder);

        // Act & Assert
        // Same object, should return true
        assertTrue(item1.equals(item1));

        // Null comparison, should return false
        assertFalse(item1.equals(null));

        // Different class comparison, should return false
        assertFalse(item1.equals(new Object()));

        // Same id, quantity, price, status, user, product, and order, should return true
        assertTrue(item1.equals(item2));

        // Different id, quantity, price, status, user, product, or order, should return false
        assertFalse(item1.equals(item3));

        // Test canEqual method
        assertTrue(item1.canEqual(item2));  // Same type, should return true
        assertFalse(item1.canEqual(new Object()));  // Different type, should return false
    }

    @Test
    void testHashCode() {
        // Arrange
        User mockUser = mock(User.class); // Mocking User
        Product mockProduct = mock(Product.class); // Mocking Product
        Order mockOrder = mock(Order.class); // Mocking Order

        // OrderItem 1
        OrderItem item1 = new OrderItem();
        item1.setId(1L);
        item1.setQuantity(2);
        item1.setPrice(BigDecimal.valueOf(100));
        item1.setStatus(OrderStatus.PENDING);
        item1.setUser(mockUser);
        item1.setProduct(mockProduct);
        item1.setOrder(mockOrder);

        // OrderItem 2 (identical to item1)
        OrderItem item2 = new OrderItem();
        item2.setId(1L);
        item2.setQuantity(2);
        item2.setPrice(BigDecimal.valueOf(100));
        item2.setStatus(OrderStatus.PENDING);
        item2.setUser(mockUser);
        item2.setProduct(mockProduct);
        item2.setOrder(mockOrder);

        // OrderItem 3 (different id, quantity, price, and status)
        OrderItem item3 = new OrderItem();
        item3.setId(2L);
        item3.setQuantity(3);
        item3.setPrice(BigDecimal.valueOf(150));
        item3.setStatus(OrderStatus.CANCELLED);
        item3.setUser(mockUser);
        item3.setProduct(mockProduct);
        item3.setOrder(mockOrder);

        // Act & Assert
        // Ensure items with the same attributes return the same hash code
        assertEquals(item1.hashCode(), item2.hashCode());

        // Ensure items with different attributes return different hash codes
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}
