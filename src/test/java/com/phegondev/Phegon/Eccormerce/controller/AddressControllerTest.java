package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.AddressDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.service.interf.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressControllerTest {

    private AddressController addressController;

    @Mock
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressController = new AddressController(addressService);
    }

    @Test
    void testSaveAndUpdateAddress_Success() {
        // Arrange
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("123 Main St");
        addressDto.setCity("New York");
        addressDto.setCountry("USA");

        Response mockResponse = Response.builder()
                .status(200)
                .message("Address saved successfully")
                .build();

        when(addressService.saveAndUpdateAddress(addressDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = addressController.saveAndUpdateAddress(addressDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(addressService, times(1)).saveAndUpdateAddress(addressDto);
    }

    @Test
    void testSaveAndUpdateAddress_InvalidInput() {
        // Arrange
        AddressDto invalidAddressDto = new AddressDto(); // Invalid DTO (missing required fields)
        Response mockResponse = Response.builder()
                .status(400)
                .message("Invalid address data")
                .build();

        when(addressService.saveAndUpdateAddress(invalidAddressDto)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = addressController.saveAndUpdateAddress(invalidAddressDto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        // Verify service interaction
        verify(addressService, times(1)).saveAndUpdateAddress(invalidAddressDto);
    }
}
