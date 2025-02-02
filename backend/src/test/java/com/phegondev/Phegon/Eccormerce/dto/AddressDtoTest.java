package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AddressDtoTest {

    @Test
    void testAddressDtoGetterAndSetter() {
        // 创建对象
        AddressDto address = new AddressDto();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10001");
        address.setCountry("USA");
        address.setCreatedAt(LocalDateTime.now());

        // 断言：确保字段正确
        assertEquals(1L, address.getId());
        assertEquals("123 Main St", address.getStreet());
        assertEquals("New York", address.getCity());
        assertEquals("NY", address.getState());
        assertEquals("10001", address.getZipCode());
        assertEquals("USA", address.getCountry());
        assertNotNull(address.getCreatedAt()); // 确保 createdAt 被正确赋值
    }

    @Test
    void testAddressDtoEqualsAndHashCode() {
        // 创建两个相同的 AddressDto 对象
        LocalDateTime now = LocalDateTime.now();
        AddressDto address1 = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, now);
        AddressDto address2 = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, now);

        // 断言：equals() 方法应该返回 true
        assertEquals(address1, address2);
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    void testAddressDtoToString() {
        AddressDto address = new AddressDto(1L, "123 Main St", "New York", "NY", "10001", "USA", null, LocalDateTime.now());

        // toString() 不应该返回 null，并且应该包含类的关键信息
        assertNotNull(address.toString());
        assertTrue(address.toString().contains("123 Main St"));
        assertTrue(address.toString().contains("New York"));
        assertTrue(address.toString().contains("NY"));
        assertTrue(address.toString().contains("10001"));
        assertTrue(address.toString().contains("USA"));
    }
}
