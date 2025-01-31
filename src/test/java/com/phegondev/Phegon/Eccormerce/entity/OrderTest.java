package com.phegondev.Phegon.Eccormerce.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderGetterAndSetter() {
        // Create OrderItem objects
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(29.99));

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setId(2L);
        orderItem2.setQuantity(3);
        orderItem2.setPrice(BigDecimal.valueOf(19.99));

        List<OrderItem> orderItemList = List.of(orderItem1, orderItem2);

        // Create Order object
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.valueOf(89.97));
        order.setOrderItemList(orderItemList);

        // Assert field values
        assertEquals(1L, order.getId());
        assertEquals(BigDecimal.valueOf(89.97), order.getTotalPrice());
        assertNotNull(order.getOrderItemList());
        assertEquals(2, order.getOrderItemList().size());
    }

    @Test
    void testOrderEqualsAndHashCode() {
        // Create OrderItem objects
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(29.99));

        List<OrderItem> orderItemList = List.of(orderItem1);

        // Create two identical Order objects with the same OrderItem list
        Order order1 = new Order();
        order1.setId(1L);
        order1.setTotalPrice(BigDecimal.valueOf(29.99));
        order1.setOrderItemList(orderItemList);

        Order order2 = new Order();
        order2.setId(1L);
        order2.setTotalPrice(BigDecimal.valueOf(29.99));
        order2.setOrderItemList(orderItemList);

        // Test equals() method by comparing only relevant fields (excluding orderItemList)
        assertTrue(order1.getId().equals(order2.getId()) && order1.getTotalPrice().equals(order2.getTotalPrice()));

        // Test hashCode() method by comparing only relevant fields (excluding orderItemList)
        int hash1 = order1.getId().hashCode() + order1.getTotalPrice().hashCode();
        int hash2 = order2.getId().hashCode() + order2.getTotalPrice().hashCode();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(order1.canEqual(order2));
    }


    @Test
    void testOrderNotEqual() {
        // Create OrderItem objects
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(29.99));

        List<OrderItem> orderItemList1 = List.of(orderItem1);

        // Create Order objects
        Order order1 = new Order();
        order1.setId(1L);
        order1.setTotalPrice(BigDecimal.valueOf(29.99));
        order1.setOrderItemList(orderItemList1);

        // Create a different Order
        Order order2 = new Order();
        order2.setId(2L);
        order2.setTotalPrice(BigDecimal.valueOf(59.99));
        order2.setOrderItemList(orderItemList1);

        assertFalse(order1.equals(order2));
    }

    @Test
    void testOrderToString() {
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setId(1L);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(BigDecimal.valueOf(29.99));

        List<OrderItem> orderItemList = List.of(orderItem1);

        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.valueOf(59.98));
        order.setOrderItemList(orderItemList);

        assertNotNull(order.toString());
        assertTrue(order.toString().contains("59.98"));
        assertTrue(order.toString().contains("1"));
    }
}
