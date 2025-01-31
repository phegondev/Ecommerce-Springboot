package com.phegondev.Phegon.Eccormerce.service.impl;

import com.phegondev.Phegon.Eccormerce.dto.LoginRequest;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.dto.UserDto;
import com.phegondev.Phegon.Eccormerce.entity.User;
import com.phegondev.Phegon.Eccormerce.enums.UserRole;
import com.phegondev.Phegon.Eccormerce.exception.InvalidCredentialsException;
import com.phegondev.Phegon.Eccormerce.exception.NotFoundException;
import com.phegondev.Phegon.Eccormerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon.Eccormerce.repository.UserRepo;
import com.phegondev.Phegon.Eccormerce.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUserSuccess() {
        // Arrange
        UserDto registrationRequest = new UserDto();
        registrationRequest.setName("John Doe");
        registrationRequest.setEmail("john.doe@example.com");
        registrationRequest.setPassword("password123");
        registrationRequest.setPhoneNumber("1234567890");
        registrationRequest.setRole("user");

        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("hashedPassword")
                .phoneNumber("1234567890")
                .role(UserRole.USER)
                .build();

        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(entityDtoMapper.mapUserToDtoBasic(user)).thenReturn(registrationRequest);

        // Act
        Response response = userService.registerUser(registrationRequest);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("User Successfully Added", response.getMessage());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void testLoginUserSuccess() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password123");

        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("hashedPassword");
        user.setRole(UserRole.USER);

        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtUtils.generateToken(user)).thenReturn("fake-jwt-token");

        // Act
        Response response = userService.loginUser(loginRequest);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("User Successfully Logged In", response.getMessage());
        assertEquals("fake-jwt-token", response.getToken());
        verify(jwtUtils, times(1)).generateToken(user);
    }

    @Test
    void testLoginUserEmailNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("nonexistent@example.com");
        loginRequest.setPassword("password123");

        when(userRepo.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.loginUser(loginRequest));
    }

    @Test
    void testLoginUserInvalidPassword() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("hashedPassword");

        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", "hashedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> userService.loginUser(loginRequest));
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        User user2 = new User();

        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        when(userRepo.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(entityDtoMapper.mapUserToDtoBasic(user1)).thenReturn(userDto1);
        when(entityDtoMapper.mapUserToDtoBasic(user2)).thenReturn(userDto2);

        // Act
        Response response = userService.getAllUsers();

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getUserList());
        assertEquals(2, response.getUserList().size());
    }

    @Test
    void testGetLoginUserSuccess() {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("john.doe@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        // Act
        User loginUser = userService.getLoginUser();

        // Assert
        assertNotNull(loginUser);
        assertEquals("john.doe@example.com", loginUser.getEmail());
    }

    @Test
    void testGetLoginUserNotFound() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("nonexistent@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userRepo.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.getLoginUser());
    }

    @Test
    void testGetUserInfoAndOrderHistory() {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");

        UserDto userDto = new UserDto();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("john.doe@example.com");
        SecurityContextHolder.setContext(securityContext);
        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        when(entityDtoMapper.mapUserToDtoPlusAddressAndOrderHistory(user)).thenReturn(userDto);

        // Act
        Response response = userService.getUserInfoAndOrderHistory();

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getUser());
    }
}
