package com.phegondev.Phegon.Eccormerce.dto;
import com.phegondev.Phegon.Eccormerce.dto.AddressDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @Test
    void testResponseWithNestedObjects() {
        // Create AddressDto using setters
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("123 Street");
        addressDto.setCity("City");
        addressDto.setState("State");
        addressDto.setZipCode("12345");

        // Create UserDto using setters
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("john.doe@example.com");
        userDto.setName("John Doe");
        userDto.setRole("USER");

        // Create a Response object using Builder and set nested DTOs
        Response response = Response.builder()
                .status(200)
                .message("Success")
                .address(addressDto)
                .user(userDto)
                .build();

        // Validate AddressDto
        assertEquals("123 Street", response.getAddress().getStreet());
        assertEquals("City", response.getAddress().getCity());
        assertEquals("State", response.getAddress().getState());
        assertEquals("12345", response.getAddress().getZipCode());

        // Validate UserDto
        assertEquals(1L, response.getUser().getId());
        assertEquals("john.doe@example.com", response.getUser().getEmail());
        assertEquals("John Doe", response.getUser().getName());
        assertEquals("USER", response.getUser().getRole());
    }
}
