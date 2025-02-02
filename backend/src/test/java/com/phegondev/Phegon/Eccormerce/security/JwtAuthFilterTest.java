package com.phegondev.Phegon.Eccormerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    private static final String VALID_TOKEN = "valid_token";
    private static final String TEST_USERNAME = "test@example.com";

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_WithValidToken_ShouldSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer " + VALID_TOKEN);
        when(jwtUtils.getUsernameFromToken(VALID_TOKEN)).thenReturn(TEST_USERNAME);
        when(customUserDetailsService.loadUserByUsername(TEST_USERNAME)).thenReturn(userDetails);
        when(jwtUtils.isTokenValid(VALID_TOKEN, userDetails)).thenReturn(true);
        when(userDetails.getUsername()).thenReturn(TEST_USERNAME);

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).getUsernameFromToken(VALID_TOKEN);
        verify(customUserDetailsService).loadUserByUsername(TEST_USERNAME);
        verify(jwtUtils).isTokenValid(VALID_TOKEN, userDetails);
        assert SecurityContextHolder.getContext().getAuthentication() != null;
        assert SecurityContextHolder.getContext().getAuthentication().getName().equals(TEST_USERNAME);
    }

    @Test
    void doFilterInternal_WithInvalidToken_ShouldNotSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer " + VALID_TOKEN);
        when(jwtUtils.getUsernameFromToken(VALID_TOKEN)).thenReturn(TEST_USERNAME);
        when(customUserDetailsService.loadUserByUsername(TEST_USERNAME)).thenReturn(userDetails);
        when(jwtUtils.isTokenValid(VALID_TOKEN, userDetails)).thenReturn(false);

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    void doFilterInternal_WithNoToken_ShouldNotSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils, never()).getUsernameFromToken(anyString());
        verify(customUserDetailsService, never()).loadUserByUsername(anyString());
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    void doFilterInternal_WithInvalidTokenFormat_ShouldNotSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("InvalidFormat");

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils, never()).getUsernameFromToken(anyString());
        verify(customUserDetailsService, never()).loadUserByUsername(anyString());
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    void doFilterInternal_WithTokenButNoBearer_ShouldNotSetAuthentication() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(VALID_TOKEN);

        // Act
        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils, never()).getUsernameFromToken(anyString());
        verify(customUserDetailsService, never()).loadUserByUsername(anyString());
        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

//    @Test
//    void doFilterInternal_WhenTokenValidationThrowsException_ShouldNotSetAuthentication() throws Exception {
//        // Arrange
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + VALID_TOKEN);
//        when(jwtUtils.getUsernameFromToken(VALID_TOKEN)).thenThrow(new RuntimeException("Token validation failed"));
//
//        // Act
//        jwtAuthFilter.doFilterInternal(request, response, filterChain);
//
//        // Assert
//        verify(filterChain).doFilter(request, response);
//        assert SecurityContextHolder.getContext().getAuthentication() == null;
//    }
}