package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.LoginRequest;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.dto.UserDto;
import com.phegondev.Phegon.Eccormerce.service.interf.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private AuthController authController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(userService);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password123");
        userDto.setPhoneNumber("1234567890");

        Response mockResponse = Response.builder()
                .status(200)
                .message("User Successfully Added")
                .build();

        when(userService.registerUser(userDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = authController.registerUser(userDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(userService, times(1)).registerUser(userDto);
    }

    @Test
    void testLoginUser_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password123");

        Response mockResponse = Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token("mock-jwt-token")
                .build();

        when(userService.loginUser(loginRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = authController.loginUser(loginRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(userService, times(1)).loginUser(loginRequest);
    }

    @Test
    void testRegisterUser_InvalidInput() {
        // Arrange
        UserDto invalidUserDto = new UserDto(); // Missing required fields
        Response mockResponse = Response.builder()
                .status(400)
                .message("Invalid user data")
                .build();

        when(userService.registerUser(invalidUserDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = authController.registerUser(invalidUserDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(userService, times(1)).registerUser(invalidUserDto);
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        // Arrange
        LoginRequest invalidLoginRequest = new LoginRequest();
        invalidLoginRequest.setEmail("john.doe@example.com");
        invalidLoginRequest.setPassword("wrong-password");

        Response mockResponse = Response.builder()
                .status(401)
                .message("Invalid credentials")
                .build();

        when(userService.loginUser(invalidLoginRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = authController.loginUser(invalidLoginRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(userService, times(1)).loginUser(invalidLoginRequest);
    }
}
