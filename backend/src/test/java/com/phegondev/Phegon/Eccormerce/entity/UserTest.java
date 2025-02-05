package com.phegondev.Phegon.Eccormerce.entity;

import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserTest {

    @Test
    void testEqualsAndCanEqual() {
        // Arrange
        Product mockProduct1 = mock(Product.class);  // Mock Product
        Product mockProduct2 = mock(Product.class);  // Mock another Product
        User mockUser1 = mock(User.class);  // Mock User
        User mockUser2 = mock(User.class);  // Mock another User
        Address mockAddress1 = mock(Address.class);
        OrderItem mockOrderItem = mock(OrderItem.class);

        // Review 1
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        user1.setPhoneNumber("1234567890");
        user1.setRole(UserRole.USER);
        user1.setOrderItemList(List.of(mockOrderItem));  // Mocking the list with a product
        user1.setAddress(mockAddress1);  // Mock Address

        // Review 2 (same as Review 1)
        User user2 = new User();
        user2.setId(1L);
        user2.setName("John Doe");
        user2.setEmail("john.doe@example.com");
        user2.setPassword("password123");
        user2.setPhoneNumber("1234567890");
        user2.setRole(UserRole.USER);
        user2.setOrderItemList(List.of(mockOrderItem));  // Mocking the list with a product
        user2.setAddress(mockAddress1);  // Mock Address

        // Review 3 (different fields)
        User user3 = new User();
        user3.setId(2L);  // Different id
        user3.setName("Jane Doe");
        user3.setEmail("jane.doe@example.com");
        user3.setPassword("password456");
        user3.setPhoneNumber("0987654321");
        user3.setRole(UserRole.ADMIN);
        user3.setOrderItemList(List.of(mockOrderItem));  // Mock another product
        user3.setAddress(mockAddress1);  // Mock different address

        // Act & Assert
        // Same object, should return true
        assertTrue(user1.equals(user1));

        // Null comparison, should return false
        assertFalse(user1.equals(null));

        // Different class comparison, should return false
        assertFalse(user1.equals(new Object()));

        // Same id, name, email, password, phoneNumber, role, orderItemList, address, should return true
        assertTrue(user1.equals(user2));

        // Different id, should return false
        assertFalse(user1.equals(user3));

        // Test canEqual method
        assertTrue(user1.canEqual(user2));  // Same type, should return true
        assertFalse(user1.canEqual(new Object()));  // Different type, should return false
    }


    @Test
    void testHashCode() {
        // Arrange
        Address mockAddress1 = mock(Address.class);
        OrderItem mockOrderItem = mock(OrderItem.class);

        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        user1.setPhoneNumber("1234567890");
        user1.setRole(UserRole.USER);
        user1.setOrderItemList(List.of(mockOrderItem));
        user1.setAddress(mockAddress1);

        User user2 = new User();
        user2.setId(1L);
        user2.setName("John Doe");
        user2.setEmail("john.doe@example.com");
        user2.setPassword("password123");
        user2.setPhoneNumber("1234567890");
        user2.setRole(UserRole.USER);
        user2.setOrderItemList(List.of(mockOrderItem));
        user2.setAddress(mockAddress1);

        User user3 = new User();
        user3.setId(2L);
        user3.setName("Jane Doe");
        user3.setEmail("jane.doe@example.com");
        user3.setPassword("password456");
        user3.setPhoneNumber("0987654321");
        user3.setRole(UserRole.ADMIN);
        user3.setOrderItemList(List.of(mockOrderItem));
        user3.setAddress(mockAddress1);

        // Act & Assert
        // Ensure users with the same attributes return the same hash code
        assertEquals(user1.hashCode(), user2.hashCode());

        // Ensure users with different attributes return different hash codes
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}
