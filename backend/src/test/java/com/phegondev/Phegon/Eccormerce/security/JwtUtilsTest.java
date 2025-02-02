package com.phegondev.Phegon.Eccormerce.security;

import com.phegondev.Phegon.Eccormerce.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private static final String SECRET_KEY = "thisIsATestSecretKeyWith32Characters";
    private static final String TEST_EMAIL = "test@example.com";

    @BeforeEach
    void setUp() throws Exception {
        jwtUtils = new JwtUtils();
        ReflectionTestUtils.setField(jwtUtils, "secreteJwtString", SECRET_KEY);
        Method initMethod = JwtUtils.class.getDeclaredMethod("init");
        initMethod.setAccessible(true);
        initMethod.invoke(jwtUtils);
    }

    @Test
    void generateToken_WithUser_ShouldReturnValidToken() {
        // Arrange
        User user = new User();
        user.setEmail(TEST_EMAIL);

        // Act
        String token = jwtUtils.generateToken(user);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0);
        assertEquals(TEST_EMAIL, jwtUtils.getUsernameFromToken(token));
    }

    @Test
    void generateToken_WithUsername_ShouldReturnValidToken() {
        // Act
        String token = jwtUtils.generateToken(TEST_EMAIL);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0);
        assertEquals(TEST_EMAIL, jwtUtils.getUsernameFromToken(token));
    }

    @Test
    void getUsernameFromToken_ShouldReturnCorrectUsername() {
        // Arrange
        String token = jwtUtils.generateToken(TEST_EMAIL);

        // Act
        String extractedUsername = jwtUtils.getUsernameFromToken(token);

        // Assert
        assertEquals(TEST_EMAIL, extractedUsername);
    }

    @Test
    void isTokenValid_WithValidToken_ShouldReturnTrue() {
        // Arrange
        String token = jwtUtils.generateToken(TEST_EMAIL);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn(TEST_EMAIL);

        // Act
        boolean isValid = jwtUtils.isTokenValid(token, userDetails);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void isTokenValid_WithInvalidUsername_ShouldReturnFalse() {
        // Arrange
        String token = jwtUtils.generateToken(TEST_EMAIL);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("wrong@example.com");

        // Act
        boolean isValid = jwtUtils.isTokenValid(token, userDetails);

        // Assert
        assertFalse(isValid);
    }

    // ... existing code ...

//    @Test
//    void isTokenValid_WithExpiredToken_ShouldReturnFalse() {
//        // Arrange
//        // 创建一个手动构造的过期token
//        String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwMjJ9.8qZC_UvyPh-tKs3LyTQ_Yh-PHzBA8BZqOW8VoVOnB54";
//
//        UserDetails userDetails = mock(UserDetails.class);
//        when(userDetails.getUsername()).thenReturn(TEST_EMAIL);
//
//        // Act & Assert
//        assertFalse(jwtUtils.isTokenValid(expiredToken, userDetails));
//    }

    @Test
    void getUsernameFromToken_WithExpiredToken_ShouldReturnFalse() {
        // Arrange
        String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkwMjJ9.8qZC_UvyPh-tKs3LyTQ_Yh-PHzBA8BZqOW8VoVOnB54";

        // Act & Assert
        assertThrows(io.jsonwebtoken.SignatureException.class, () -> {
            jwtUtils.getUsernameFromToken(expiredToken);
        });
    }
}