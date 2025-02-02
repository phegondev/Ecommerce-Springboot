package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testUserDtoGetterAndSetter() {
        // 创建 AddressDto 对象
        AddressDto address = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, null);

        // 创建 OrderItemDto 列表
        OrderItemDto orderItem1 = new OrderItemDto(1L, 2, null, "Shipped", null, null, null);
        OrderItemDto orderItem2 = new OrderItemDto(2L, 1, null, "Pending", null, null, null);
        List<OrderItemDto> orderItems = Arrays.asList(orderItem1, orderItem2);

        // 创建 UserDto 对象
        UserDto user = new UserDto();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("John Doe");
        user.setPhoneNumber("123-456-7890");
        user.setPassword("password123");
        user.setRole("USER");
        user.setOrderItemList(orderItems);
        user.setAddress(address);

        // 断言字段正确
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("John Doe", user.getName());
        assertEquals("123-456-7890", user.getPhoneNumber());
        assertEquals("password123", user.getPassword());
        assertEquals("USER", user.getRole());
        assertNotNull(user.getOrderItemList());
        assertEquals(2, user.getOrderItemList().size());
        assertNotNull(user.getAddress());
        assertEquals("123 Main St", user.getAddress().getStreet());
    }

    @Test
    void testUserDtoEqualsAndHashCode() {
        AddressDto address = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, null);
        OrderItemDto orderItem1 = new OrderItemDto(1L, 2, null, "Shipped", null, null, null);
        List<OrderItemDto> orderItems = List.of(orderItem1);

        UserDto user1 = new UserDto(1L, "test@example.com", "John Doe", "123-456-7890", "password123", "USER", orderItems, address);
        UserDto user2 = new UserDto(1L, "test@example.com", "John Doe", "123-456-7890", "password123", "USER", orderItems, address);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserDtoToString() {
        AddressDto address = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, null);
        List<OrderItemDto> orderItems = List.of(new OrderItemDto(1L, 2, null, "Shipped", null, null, null));

        UserDto user = new UserDto(1L, "test@example.com", "John Doe", "123-456-7890", "password123", "USER", orderItems, address);

        assertNotNull(user.toString());
        assertTrue(user.toString().contains("test@example.com"));
        assertTrue(user.toString().contains("John Doe"));
        assertTrue(user.toString().contains("USER"));
    }
}
