package com.phegondev.Phegon.Eccormerce.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidLoginRequest() {
        // Create a valid LoginRequest
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        // Validate
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // Assert no validation errors
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidLoginRequest_EmailBlank() {
        // Create an invalid LoginRequest with blank email
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("password123");

        // Validate
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // Assert one validation error
        assertEquals(1, violations.size());
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("Email is required", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidLoginRequest_PasswordBlank() {
        // Create an invalid LoginRequest with blank password
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("");

        // Validate
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // Assert one validation error
        assertEquals(1, violations.size());
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("Password is required", violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    void testInvalidLoginRequest_AllBlank() {
        // Create an invalid LoginRequest with both fields blank
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("");

        // Validate
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        // Assert two validation errors
        assertEquals(2, violations.size());
    }
}
