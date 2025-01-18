package com.phegondev.Phegon.Eccormerce.security;

import com.phegondev.Phegon.Eccormerce.entity.User;
import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AuthUserTest {

    private AuthUser authUser;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .email("test@example.com")
                .password("password123")
                .role(UserRole.USER) // Assuming `Role` is an enum with USER, ADMIN, etc.
                .build();

        authUser = AuthUser.builder()
                .user(user)
                .build();
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("USER")));
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", authUser.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals("test@example.com", authUser.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(authUser.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(authUser.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(authUser.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(authUser.isEnabled());
    }
}
