package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.OrderRequest;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import com.phegondev.Phegon.Eccormerce.service.interf.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderItemControllerTest {

    private OrderItemController orderItemController;

    @Mock
    private OrderItemService orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemController = new OrderItemController(orderItemService);
    }

    @Test
    void testPlaceOrder_Success() {
        // Arrange
        OrderRequest orderRequest = new OrderRequest();
        Response mockResponse = Response.builder()
                .status(200)
                .message("Order was successfully placed")
                .build();

        when(orderItemService.placeOrder(orderRequest)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = orderItemController.placeOrder(orderRequest);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        verify(orderItemService, times(1)).placeOrder(orderRequest);
    }

    @Test
    void testUpdateOrderItemStatus_Success() {
        // Arrange
        Long orderItemId = 1L;
        String status = "COMPLETED";
        Response mockResponse = Response.builder()
                .status(200)
                .message("Order status updated successfully")
                .build();

        when(orderItemService.updateOrderItemStatus(orderItemId, status)).thenReturn(mockResponse);

        // Act
        ResponseEntity<Response> responseEntity = orderItemController.updateOrderItemStatus(orderItemId, status);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockResponse, responseEntity.getBody());

        verify(orderItemService, times(1)).updateOrderItemStatus(orderItemId, status);
    }

//    @Test
//    void testFilterOrderItems_Success() {
//        // Arrange
//        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59);
//        String status = "PENDING";
//        Long itemId = 5L;
//        int page = 0;
//        int size = 10;
//
//        Response mockResponse = Response.builder()
//                .status(200)
//                .message("Order items filtered successfully")
//                .build();
//
//        when(orderItemService.filterOrderItems(
//                OrderStatus.PENDING, startDate, endDate, itemId, PageRequest.of(page, size)))
//                .thenReturn(mockResponse);
//
//        // Act
//        ResponseEntity<Response> responseEntity = orderItemController.filterOrderItems(
//                startDate, endDate, status, itemId, page, size);
//
//        // Assert
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(mockResponse, responseEntity.getBody());
//
//        verify(orderItemService, times(1)).filterOrderItems(
//                OrderStatus.PENDING, startDate, endDate, itemId, PageRequest.of(page, size));
//    }
//
//    @Test
//    void testFilterOrderItems_NullParams() {
//        // Arrange
//        int page = 0;
//        int size = 1000;
//
//        Response mockResponse = Response.builder()
//                .status(200)
//                .message("Order items filtered successfully with null params")
//                .build();
//
//        when(orderItemService.filterOrderItems(
//                null, null, null, null, PageRequest.of(page, size)))
//                .thenReturn(mockResponse);
//
//        // Act
//        ResponseEntity<Response> responseEntity = orderItemController.filterOrderItems(
//                null, null, null, null, page, size);
//
//        // Assert
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(mockResponse, responseEntity.getBody());
//
//        verify(orderItemService, times(1)).filterOrderItems(
//                null, null, null, null, PageRequest.of(page, size));
//    }
}
