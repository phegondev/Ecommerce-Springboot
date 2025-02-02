package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    @Test
    void testOrderDtoGetterAndSetter() {
        // 创建 OrderItemDto 列表
        OrderItemDto item1 = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, LocalDateTime.now());
        OrderItemDto item2 = new OrderItemDto(2L, 1, BigDecimal.valueOf(9.99), "Pending", null, null, LocalDateTime.now());
        List<OrderItemDto> orderItems = Arrays.asList(item1, item2);

        // 创建 OrderDto 对象
        OrderDto order = new OrderDto();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.valueOf(49.97));
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderItemList(orderItems);

        // 断言字段正确
        assertEquals(1L, order.getId());
        assertEquals(BigDecimal.valueOf(49.97), order.getTotalPrice());
        assertNotNull(order.getCreatedAt());
        assertEquals(2, order.getOrderItemList().size());
    }

    @Test
    void testOrderDtoEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        OrderItemDto item1 = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, now);
        List<OrderItemDto> orderItems = List.of(item1);

        OrderDto order1 = new OrderDto(1L, BigDecimal.valueOf(49.97), now, orderItems);
        OrderDto order2 = new OrderDto(1L, BigDecimal.valueOf(49.97), now, orderItems);

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    void testOrderDtoToString() {
        OrderItemDto item1 = new OrderItemDto(1L, 2, BigDecimal.valueOf(19.99), "Shipped", null, null, LocalDateTime.now());
        List<OrderItemDto> orderItems = List.of(item1);

        OrderDto order = new OrderDto(1L, BigDecimal.valueOf(49.97), LocalDateTime.now(), orderItems);

        assertNotNull(order.toString());
        assertTrue(order.toString().contains("49.97"));
    }
}
