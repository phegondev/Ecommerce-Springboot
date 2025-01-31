package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.service.interf.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void testGetAllUsers_Success() {
        // Arrange
        Response mockResponse = Response.builder()
                .status(200)
                .message("All users retrieved successfully")
                .build();
        when(userService.getAllUsers()).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = userController.getAllUsers();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("All users retrieved successfully", response.getBody().getMessage());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_Failure() {
        // Arrange
        when(userService.getAllUsers()).thenThrow(new RuntimeException("Failed to retrieve users"));

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userController.getAllUsers()
        );
        assertEquals("Failed to retrieve users", exception.getMessage());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserInfoAndOrderHistory_Success() {
        // Arrange
        Response mockResponse = Response.builder()
                .status(200)
                .message("User info and order history retrieved successfully")
                .build();
        when(userService.getUserInfoAndOrderHistory()).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> response = userController.getUserInfoAndOrderHistory();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User info and order history retrieved successfully", response.getBody().getMessage());
        verify(userService, times(1)).getUserInfoAndOrderHistory();
    }

    @Test
    void testGetUserInfoAndOrderHistory_Failure() {
        // Arrange
        when(userService.getUserInfoAndOrderHistory()).thenThrow(new RuntimeException("Failed to retrieve user info"));

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userController.getUserInfoAndOrderHistory()
        );
        assertEquals("Failed to retrieve user info", exception.getMessage());
        verify(userService, times(1)).getUserInfoAndOrderHistory();
    }
}
