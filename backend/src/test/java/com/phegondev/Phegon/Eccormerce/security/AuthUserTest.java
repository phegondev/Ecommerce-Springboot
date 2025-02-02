package com.phegondev.Phegon.Eccormerce.security;

import com.phegondev.Phegon.Eccormerce.entity.User;
import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AuthUserTest {

    @Test
    void testGetAuthorities() {
        User user = new User();
        user.setRole(UserRole.ADMIN);
        AuthUser authUser = AuthUser.builder().user(user).build();

        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals("ADMIN", authorities.iterator().next().getAuthority());
    }

    @Test
    void testGetPassword() {
        User user = new User();
        user.setPassword("testPassword");
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertEquals("testPassword", authUser.getPassword());
    }

    @Test
    void testGetUsername() {
        User user = new User();
        user.setEmail("test@example.com");
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertEquals("test@example.com", authUser.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        User user = new User();
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertTrue(authUser.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        User user = new User();
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertTrue(authUser.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        User user = new User();
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertTrue(authUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        User user = new User();
        AuthUser authUser = AuthUser.builder().user(user).build();

        assertTrue(authUser.isEnabled());
    }

//    @Test
//    void testEquals() {
//        User user1 = new User();
//        user1.setId(1L);
//        user1.setEmail("user1@example.com");
//        user1.setPassword("password123");
//        user1.setRole(UserRole.ADMIN);
//
//        User user2 = new User();
//        user2.setId(1L);
//        user2.setEmail("user1@example.com");
//        user2.setPassword("password123");
//        user2.setRole(UserRole.ADMIN);
//
//        AuthUser authUser1 = AuthUser.builder().user(user1).build();
//        AuthUser authUser2 = AuthUser.builder().user(user2).build();
//
//        assertTrue(authUser1.equals(authUser2)); // Now should return true as it compares the email
//    }

//    @Test
//    void testHashCode() {
//        User user1 = new User();
//        user1.setId(1L);
//        user1.setEmail("user1@example.com");
//        user1.setPassword("password123");
//        user1.setRole(UserRole.ADMIN);
//
//        AuthUser authUser1 = AuthUser.builder().user(user1).build();
//
//        User user2 = new User();
//        user2.setId(1L);
//        user2.setEmail("user1@example.com");
//        user2.setPassword("password123");
//        user2.setRole(UserRole.ADMIN);
//
//        AuthUser authUser2 = AuthUser.builder().user(user2).build();
//
//        assertEquals(authUser1.hashCode(), authUser2.hashCode()); // Same hashCode for equal objects
//    }


    @Test
    void testSetUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        user.setRole(UserRole.USER);

        AuthUser authUser = AuthUser.builder().user(user).build();
        assertEquals("test@example.com", authUser.getUsername()); // Check the username after set

        // Change the user
        User newUser = new User();
        newUser.setEmail("new@example.com");
        authUser.setUser(newUser);

        assertEquals("new@example.com", authUser.getUsername()); // Check the new username after set
    }

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        AuthUser authUser = AuthUser.builder().user(user).build();

        assertNotNull(authUser.toString()); // Ensure toString doesn't return null
        assertTrue(authUser.toString().contains("test@example.com")); // Check if toString contains user email
    }
}
