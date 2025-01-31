package com.phegondev.Phegon.Eccormerce.exception;

import com.phegondev.Phegon.Eccormerce.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleAllException() {
        // Arrange
        Exception exception = new Exception("General error occurred");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Response> responseEntity = globalExceptionHandler.handleAllException(exception, request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().getStatus());
        assertEquals("General error occurred", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleNotFoundException() {
        // Arrange
        NotFoundException exception = new NotFoundException("Resource not found");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Response> responseEntity = globalExceptionHandler.handleNotFoundException(exception, request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus());
        assertEquals("Resource not found", responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleInvalidCredentialsException() {
        // Arrange
        InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials provided");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Response> responseEntity = globalExceptionHandler.handleInvalidCredentialsExceptionException(exception, request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatus());
        assertEquals("Invalid credentials provided", responseEntity.getBody().getMessage());
    }
}
