package com.phegondev.Phegon.Eccormerce.dto;

import com.phegondev.Phegon.Eccormerce.entity.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRequestTest {

    @Test
    void testOrderRequestGetterAndSetter() {
        // 创建 OrderItemRequest 列表
        OrderItemRequest item1 = new OrderItemRequest();
        item1.setProductId(1L);
        item1.setQuantity(3);

        OrderItemRequest item2 = new OrderItemRequest();
        item2.setProductId(2L);
        item2.setQuantity(1);

        List<OrderItemRequest> items = List.of(item1, item2);

        // 创建 Payment 对象（模拟对象，假设 Payment 有无参构造方法）
        Payment payment = new Payment();

        // 创建 OrderRequest 对象
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTotalPrice(BigDecimal.valueOf(99.99));
        orderRequest.setItems(items);
        orderRequest.setPaymentInfo(payment);

        // 断言字段正确
        assertEquals(BigDecimal.valueOf(99.99), orderRequest.getTotalPrice());
        assertNotNull(orderRequest.getItems());
        assertEquals(2, orderRequest.getItems().size());
        assertNotNull(orderRequest.getPaymentInfo());
    }

    @Test
    void testOrderRequestEqualsAndHashCode() {
        // 创建 OrderItemRequest 列表
        OrderItemRequest item1 = new OrderItemRequest();
        item1.setProductId(1L);
        item1.setQuantity(3);

        List<OrderItemRequest> items = List.of(item1);

        // 创建 Payment 对象
        Payment payment = new Payment();

        // 创建两个相同的 OrderRequest 对象
        OrderRequest order1 = new OrderRequest();
        order1.setTotalPrice(BigDecimal.valueOf(99.99));
        order1.setItems(items);
        order1.setPaymentInfo(payment);

        OrderRequest order2 = new OrderRequest();
        order2.setTotalPrice(BigDecimal.valueOf(99.99));
        order2.setItems(items);
        order2.setPaymentInfo(payment);

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    void testOrderRequestToString() {
        // 创建 OrderItemRequest 列表
        OrderItemRequest item1 = new OrderItemRequest();
        item1.setProductId(1L);
        item1.setQuantity(3);

        List<OrderItemRequest> items = List.of(item1);

        // 创建 OrderRequest 对象
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTotalPrice(BigDecimal.valueOf(99.99));
        orderRequest.setItems(items);
        orderRequest.setPaymentInfo(new Payment());

        assertNotNull(orderRequest.toString());
        assertTrue(orderRequest.toString().contains("99.99"));
    }
}
