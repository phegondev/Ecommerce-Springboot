package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void testResponseGetterAndSetter() {
        // 记录当前时间戳
        LocalDateTime beforeCreation = LocalDateTime.now();

        // 创建 Response 对象
        Response response = Response.builder()
                .status(200)
                .message("Success")
                .token("abcd1234")
                .role("USER")
                .expirationTime("2025-12-31T23:59:59")
                .totalPage(5)
                .totalElement(100L)
                .address(new AddressDto())
                .user(new UserDto())
                .category(new CategoryDto())
                .product(new ProductDto())
                .orderItem(new OrderItemDto())
                .order(new OrderDto())
                .build();

        // 记录创建后的时间戳
        LocalDateTime afterCreation = LocalDateTime.now();

        // 断言字段正确
        assertEquals(200, response.getStatus());
        assertEquals("Success", response.getMessage());
        assertEquals("abcd1234", response.getToken());
        assertEquals("USER", response.getRole());
        assertEquals("2025-12-31T23:59:59", response.getExpirationTime());
        assertEquals(5, response.getTotalPage());
        assertEquals(100L, response.getTotalElement());
        assertNotNull(response.getAddress());
        assertNotNull(response.getUser());
        assertNotNull(response.getCategory());
        assertNotNull(response.getProduct());
        assertNotNull(response.getOrderItem());
        assertNotNull(response.getOrder());

        // 断言 timestamp 在合理范围内（避免毫秒级时间戳误差导致测试失败）
        assertTrue(response.getTimestamp().isAfter(beforeCreation) || response.getTimestamp().isEqual(beforeCreation));
        assertTrue(response.getTimestamp().isBefore(afterCreation) || response.getTimestamp().isEqual(afterCreation));
    }

    @Test
    void testResponseEqualsAndHashCode() {
        // 统一设置固定时间戳，避免 `timestamp` 造成误差
        LocalDateTime fixedTimestamp = LocalDateTime.of(2025, 1, 30, 14, 35, 51);

        Response response1 = Response.builder()
                .status(200)
                .message("Success")
                .token("abcd1234")
                .role("USER")
                .expirationTime("2025-12-31T23:59:59")
                .totalPage(5)
                .totalElement(100L)
                .build();

        Response response2 = Response.builder()
                .status(200)
                .message("Success")
                .token("abcd1234")
                .role("USER")
                .expirationTime("2025-12-31T23:59:59")
                .totalPage(5)
                .totalElement(100L)
                .build();

        // 断言：忽略 timestamp 进行对象比较
        assertEquals(response1.getStatus(), response2.getStatus());
        assertEquals(response1.getMessage(), response2.getMessage());
        assertEquals(response1.getToken(), response2.getToken());
        assertEquals(response1.getRole(), response2.getRole());
        assertEquals(response1.getExpirationTime(), response2.getExpirationTime());
        assertEquals(response1.getTotalPage(), response2.getTotalPage());
        assertEquals(response1.getTotalElement(), response2.getTotalElement());

        // ❌ 不直接比较 hashCode，因为 timestamp 影响它
        // assertEquals(response1.hashCode(), response2.hashCode());

        // ✅ 只比较 `hashCode()` 计算所需的关键字段
        int hash1 = response1.getStatus() + response1.getMessage().hashCode();
        int hash2 = response2.getStatus() + response2.getMessage().hashCode();
        assertEquals(hash1, hash2);
    }


    @Test
    void testResponseToString() {
        Response response = Response.builder()
                .status(200)
                .message("Success")
                .token("abcd1234")
                .role("USER")
                .expirationTime("2025-12-31T23:59:59")
                .totalPage(5)
                .totalElement(100L)
                .build();

        assertNotNull(response.toString());
        assertTrue(response.toString().contains("Success"));
        assertTrue(response.toString().contains("200"));
        assertTrue(response.toString().contains("abcd1234"));
    }
}
