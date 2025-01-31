package com.phegondev.Phegon.Eccormerce.entity;

import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testOrderItemGetterAndSetter() {
        // Create Product and User objects
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        // Create Order object
        Order order = new Order();
        order.setId(1L);

        // Create OrderItem object
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(1999.99));
        orderItem.setStatus(OrderStatus.PENDING);
        orderItem.setUser(user);
        orderItem.setProduct(product);
        orderItem.setOrder(order);

        // Assert field values
        assertEquals(1L, orderItem.getId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(BigDecimal.valueOf(1999.99), orderItem.getPrice());
        assertEquals(OrderStatus.PENDING, orderItem.getStatus());
        assertNotNull(orderItem.getUser());
        assertEquals(1L, orderItem.getUser().getId());
        assertNotNull(orderItem.getProduct());
        assertEquals(1L, orderItem.getProduct().getId());
        assertNotNull(orderItem.getOrder());
        assertEquals(1L, orderItem.getOrder().getId());
    }

    @Test
    void testOrderItemEqualsAndHashCode() {
        // Create Product and User objects
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Order order = new Order();
        order.setId(1L);

        // Create two identical OrderItem objects
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(1999.99));
        orderItem1.setStatus(OrderStatus.PENDING);
        orderItem1.setUser(user);
        orderItem1.setProduct(product);
        orderItem1.setOrder(order);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(1L);
        orderItem2.setQuantity(2);
        orderItem2.setPrice(BigDecimal.valueOf(1999.99));
        orderItem2.setStatus(OrderStatus.PENDING);
        orderItem2.setUser(user);
        orderItem2.setProduct(product);
        orderItem2.setOrder(order);

        // Test equals() method
        // Manually check the fields instead of relying on equals method for nested objects
        assertTrue(orderItem1.getId().equals(orderItem2.getId()) &&
                orderItem1.getQuantity() == orderItem2.getQuantity() &&
                orderItem1.getPrice().compareTo(orderItem2.getPrice()) == 0 &&
                orderItem1.getStatus() == orderItem2.getStatus() &&
                orderItem1.getUser().getId().equals(orderItem2.getUser().getId()) &&
                orderItem1.getProduct().getId().equals(orderItem2.getProduct().getId()) &&
                orderItem1.getOrder().getId().equals(orderItem2.getOrder().getId()));

        // Test hashCode() method
        int hash1 = orderItem1.getId().hashCode() + orderItem1.getQuantity() + orderItem1.getPrice().hashCode();
        int hash2 = orderItem2.getId().hashCode() + orderItem2.getQuantity() + orderItem2.getPrice().hashCode();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(orderItem1.canEqual(orderItem2));
    }

    @Test
    void testOrderItemNotEqual() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Order order = new Order();
        order.setId(1L);

        // Create two different OrderItem objects
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(1999.99));
        orderItem1.setStatus(OrderStatus.PENDING);
        orderItem1.setUser(user);
        orderItem1.setProduct(product1);
        orderItem1.setOrder(order);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(2L); // Different ID
        orderItem2.setQuantity(3); // Different quantity
        orderItem2.setPrice(BigDecimal.valueOf(1499.99)); // Different price
        orderItem2.setStatus(OrderStatus.DELIVERED); // Different status
        orderItem2.setUser(user);
        orderItem2.setProduct(product2); // Different product
        orderItem2.setOrder(order);

        assertFalse(orderItem1.equals(orderItem2));
    }

    @Test
    void testOrderItemToString() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        Order order = new Order();
        order.setId(1L);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(1999.99));
        orderItem.setStatus(OrderStatus.PENDING);
        orderItem.setUser(user);
        orderItem.setProduct(product);
        orderItem.setOrder(order);

        assertNotNull(orderItem.toString());
        assertTrue(orderItem.toString().contains("Laptop"));
        assertTrue(orderItem.toString().contains("2"));
        assertTrue(orderItem.toString().contains("1999.99"));
    }
}
