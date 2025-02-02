package com.phegondev.Phegon.Eccormerce.service.impl;

import com.phegondev.Phegon.Eccormerce.dto.OrderItemDto;
import com.phegondev.Phegon.Eccormerce.dto.OrderItemRequest;
import com.phegondev.Phegon.Eccormerce.dto.OrderRequest;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.entity.Order;
import com.phegondev.Phegon.Eccormerce.entity.OrderItem;
import com.phegondev.Phegon.Eccormerce.entity.Product;
import com.phegondev.Phegon.Eccormerce.entity.User;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import com.phegondev.Phegon.Eccormerce.exception.NotFoundException;
import com.phegondev.Phegon.Eccormerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon.Eccormerce.repository.OrderItemRepo;
import com.phegondev.Phegon.Eccormerce.repository.OrderRepo;
import com.phegondev.Phegon.Eccormerce.repository.ProductRepo;
import com.phegondev.Phegon.Eccormerce.service.interf.UserService;
import com.phegondev.Phegon.Eccormerce.specification.OrderItemSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemServiceImplTest {

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private UserService userService;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrderSuccess() {
        // Arrange
        User user = new User();
        when(userService.getLoginUser()).thenReturn(user);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("100"));

        // Creating OrderItemRequest manually
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setProductId(1L);  // Assuming these fields exist
        orderItemRequest.setQuantity(2);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(orderItemRequest)); // Adding OrderItemRequest to OrderRequest

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Response response = orderItemService.placeOrder(orderRequest);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Order was successfully placed", response.getMessage());
        verify(orderRepo, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrderProductNotFound() {
        // Arrange
        when(userService.getLoginUser()).thenReturn(new User());

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setProductId(1L); // Assuming these fields exist

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Collections.singletonList(orderItemRequest));

        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.placeOrder(orderRequest));
    }


    @Test
    void testUpdateOrderItemStatusSuccess() {
        // Arrange
        Long orderItemId = 1L;
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemId);

        when(orderItemRepo.findById(orderItemId)).thenReturn(Optional.of(orderItem));

        // Act
        Response response = orderItemService.updateOrderItemStatus(orderItemId, "SHIPPED");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Order status updated successfully", response.getMessage());
        assertEquals(OrderStatus.SHIPPED, orderItem.getStatus());
        verify(orderItemRepo, times(1)).save(orderItem);
    }

    @Test
    void testUpdateOrderItemStatusNotFound() {
        // Arrange
        Long orderItemId = 1L;
        when(orderItemRepo.findById(orderItemId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.updateOrderItemStatus(orderItemId, "SHIPPED"));
    }

    @Test
    void testFilterOrderItemsSuccess() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);

        List<OrderItem> orderItemList = Collections.singletonList(orderItem);
        Page<OrderItem> orderItemPage = new PageImpl<>(orderItemList);

        when(orderItemRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(orderItemPage);
        when(entityDtoMapper.mapOrderItemToDtoPlusProductAndUser(orderItem)).thenReturn(new OrderItemDto());

        // Act
        Response response = orderItemService.filterOrderItems(OrderStatus.PENDING, null, null, null, pageable);

        // Assert
        assertEquals(200, response.getStatus());
        assertNotNull(response.getOrderItemList());
        assertEquals(1, response.getOrderItemList().size());
    }

    @Test
    void testFilterOrderItemsNoResults() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        Page<OrderItem> emptyPage = new PageImpl<>(Collections.emptyList());
        when(orderItemRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(emptyPage);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderItemService.filterOrderItems(OrderStatus.PENDING, null, null, null, pageable));
    }
}
