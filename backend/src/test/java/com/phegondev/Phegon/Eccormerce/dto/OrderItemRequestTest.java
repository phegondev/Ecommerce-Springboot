package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemRequestTest {

    @Test
    void testOrderItemRequestGetterAndSetter() {
        // 创建 OrderItemRequest 对象
        OrderItemRequest request = new OrderItemRequest();
        request.setProductId(1L);
        request.setQuantity(5);

        // 断言字段正确
        assertEquals(1L, request.getProductId());
        assertEquals(5, request.getQuantity());
    }

    @Test
    void testOrderItemRequestEqualsAndHashCode() {
        OrderItemRequest request1 = new OrderItemRequest();
        request1.setProductId(1L);
        request1.setQuantity(5);

        OrderItemRequest request2 = new OrderItemRequest();
        request2.setProductId(1L);
        request2.setQuantity(5);

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void testOrderItemRequestToString() {
        OrderItemRequest request = new OrderItemRequest();
        request.setProductId(1L);
        request.setQuantity(5);

        assertNotNull(request.toString());
        assertTrue(request.toString().contains("productId=1"));
        assertTrue(request.toString().contains("quantity=5"));
    }
}
