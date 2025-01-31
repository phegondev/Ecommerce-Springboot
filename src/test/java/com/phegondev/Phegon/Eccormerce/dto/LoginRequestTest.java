package com.phegondev.Phegon.Eccormerce.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testLoginRequestGetterAndSetter() {
        // 创建 LoginRequest 对象
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("securePassword");

        // 断言字段正确
        assertEquals("test@example.com", loginRequest.getEmail());
        assertEquals("securePassword", loginRequest.getPassword());
    }

    @Test
    void testLoginRequestValidationSuccess() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // 断言没有验证错误
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLoginRequestValidationFailure() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(""); // 空值，违反 @NotBlank 约束
        loginRequest.setPassword(""); // 空值，违反 @NotBlank 约束

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // 断言有验证错误
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size()); // email 和 password 各有一个验证错误

        for (ConstraintViolation<LoginRequest> violation : violations) {
            String message = violation.getMessage();
            assertTrue(message.equals("Email is required") || message.equals("Password is required"));
        }
    }

    @Test
    void testLoginRequestEqualsAndHashCode() {
        LoginRequest request1 = new LoginRequest();
        request1.setEmail("test@example.com");
        request1.setPassword("password123");

        LoginRequest request2 = new LoginRequest();
        request2.setEmail("test@example.com");
        request2.setPassword("password123");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void testLoginRequestToString() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        assertNotNull(loginRequest.toString());
        assertTrue(loginRequest.toString().contains("test@example.com"));
    }
}
