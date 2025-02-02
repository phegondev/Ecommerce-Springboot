package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDtoTest {

    @Test
    void testOrderItemDtoGetterAndSetter() {
        // 创建 OrderItemDto 对象
        OrderItemDto orderItem = new OrderItemDto();
        orderItem.setId(1L);
        orderItem.setQuantity(3);
        orderItem.setPrice(BigDecimal.valueOf(29.99));
        orderItem.setStatus("Delivered");
        orderItem.setCreatedAt(LocalDateTime.now());

        // 断言字段正确
        assertEquals(1L, orderItem.getId());
        assertEquals(3, orderItem.getQuantity());
        assertEquals(BigDecimal.valueOf(29.99), orderItem.getPrice());
        assertEquals("Delivered", orderItem.getStatus());
        assertNotNull(orderItem.getCreatedAt());
    }

    @Test
    void testOrderItemDtoEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        OrderItemDto item1 = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, now);
        OrderItemDto item2 = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, now);

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    void testOrderItemDtoToString() {
        OrderItemDto item = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, LocalDateTime.now());

        assertNotNull(item.toString());
        assertTrue(item.toString().contains("19.99"));
    }
}
