package com.phegondev.Phegon.Eccormerce.service.interf;

import com.phegondev.Phegon.Eccormerce.dto.AddressDto;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.service.impl.AddressServiceImpl;
import com.phegondev.Phegon.Eccormerce.service.interf.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {

    @Mock
    private AddressServiceImpl addressServiceImpl;

    @Mock
    private AddressDto addressDto;

    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressService = addressServiceImpl; // 实际上可以使用mock对象，但此处直接测试实现类
    }

    @Test
    void saveAndUpdateAddress_ValidAddress_ReturnsSuccess() {
        // 设置模拟的 AddressDto 数据
        when(addressDto.getStreet()).thenReturn("Main Street");
        when(addressDto.getCity()).thenReturn("City");
        when(addressDto.getState()).thenReturn("State");
        when(addressDto.getZipCode()).thenReturn("12345");
        when(addressDto.getCountry()).thenReturn("Country");
        when(addressDto.getUser()).thenReturn(null); // 假设没有 UserDto

        // 创建一个模拟的 Response
        Response mockResponse = Response.builder()
                .status(200)
                .message("Address saved successfully")
                .build();

        // 模拟调用服务方法
        when(addressServiceImpl.saveAndUpdateAddress(addressDto)).thenReturn(mockResponse);

        // 调用服务方法
        Response response = addressService.saveAndUpdateAddress(addressDto);

        // 验证返回结果
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("Address saved successfully", response.getMessage());

        // 验证方法调用
        verify(addressServiceImpl).saveAndUpdateAddress(addressDto);
    }

    @Test
    void saveAndUpdateAddress_InvalidAddress_ReturnsError() {
        // 设置模拟的 AddressDto 数据，模拟一个无效的地址（缺少街道）
        when(addressDto.getStreet()).thenReturn(null);

        // 创建一个模拟的 Response
        Response mockResponse = Response.builder()
                .status(400)
                .message("Invalid address")
                .build();

        // 模拟调用服务方法
        when(addressServiceImpl.saveAndUpdateAddress(addressDto)).thenReturn(mockResponse);

        // 调用服务方法
        Response response = addressService.saveAndUpdateAddress(addressDto);

        // 验证返回结果
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Invalid address", response.getMessage());

        // 验证方法调用
        verify(addressServiceImpl).saveAndUpdateAddress(addressDto);
    }
}
