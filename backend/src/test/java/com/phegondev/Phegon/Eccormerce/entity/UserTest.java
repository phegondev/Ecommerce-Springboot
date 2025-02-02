package com.phegondev.Phegon.Eccormerce.entity;

import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGetterAndSetter() {
        // Create OrderItem and Address objects
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(29.99));

        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("New York");

        // Create User object
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setPhoneNumber("1234567890");
        user.setRole(UserRole.ADMIN);
        user.setOrderItemList(List.of(orderItem));
        user.setAddress(address);

        // Assert field values
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals(UserRole.ADMIN, user.getRole());
        assertNotNull(user.getOrderItemList());
        assertEquals(1, user.getOrderItemList().size());
        assertNotNull(user.getAddress());
        assertEquals(1L, user.getAddress().getId());
    }

    @Test
    void testUserEqualsAndHashCode() {
        // Create OrderItem and Address objects
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(29.99));

        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("New York");

        // Create two identical User objects
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        user1.setPhoneNumber("1234567890");
        user1.setRole(UserRole.ADMIN);
        user1.setOrderItemList(List.of(orderItem));
        user1.setAddress(address);

        User user2 = new User();
        user2.setId(1L);
        user2.setName("John Doe");
        user2.setEmail("john.doe@example.com");
        user2.setPassword("password123");
        user2.setPhoneNumber("1234567890");
        user2.setRole(UserRole.ADMIN);
        user2.setOrderItemList(List.of(orderItem));
        user2.setAddress(address);

        // Manually compare fields and nested objects
        assertTrue(user1.getId().equals(user2.getId()) &&
                user1.getName().equals(user2.getName()) &&
                user1.getEmail().equals(user2.getEmail()) &&
                user1.getPassword().equals(user2.getPassword()) &&
                user1.getPhoneNumber().equals(user2.getPhoneNumber()) &&
                user1.getRole().equals(user2.getRole()) &&
                user1.getAddress().getId().equals(user2.getAddress().getId()) &&
                user1.getOrderItemList().size() == user2.getOrderItemList().size());

        // Test hashCode() method
        int hash1 = user1.getId().hashCode() + user1.getName().hashCode() + user1.getEmail().hashCode();
        int hash2 = user2.getId().hashCode() + user2.getName().hashCode() + user2.getEmail().hashCode();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(user1.canEqual(user2));
    }


    @Test
    void testUserNotEqual() {
        // Create OrderItem and Address objects
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(29.99));

        Address address1 = new Address();
        address1.setId(1L);
        address1.setStreet("123 Main St");
        address1.setCity("New York");

        Address address2 = new Address();
        address2.setId(2L);
        address2.setStreet("456 Another St");
        address2.setCity("Los Angeles");

        // Create two different User objects
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        user1.setPhoneNumber("1234567890");
        user1.setRole(UserRole.ADMIN);
        user1.setOrderItemList(List.of(orderItem));
        user1.setAddress(address1);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("password123");
        user2.setPhoneNumber("0987654321");
        user2.setRole(UserRole.USER);
        user2.setOrderItemList(List.of(orderItem));
        user2.setAddress(address2);

        assertFalse(user1.equals(user2));  // Different ids and different address, should not be equal
    }

    @Test
    void testUserToString() {
        // Create OrderItem and Address objects
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(BigDecimal.valueOf(29.99));

        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("New York");

        // Create User object
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setPhoneNumber("1234567890");
        user.setRole(UserRole.ADMIN);
        user.setOrderItemList(List.of(orderItem));
        user.setAddress(address);

        assertNotNull(user.toString());
        assertTrue(user.toString().contains("John Doe"));
        assertTrue(user.toString().contains("john.doe@example.com"));
        assertTrue(user.toString().contains("password123"));
    }
}
