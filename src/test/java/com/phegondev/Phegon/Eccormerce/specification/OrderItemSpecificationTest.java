package com.phegondev.Phegon.Eccormerce.specification;

import com.phegondev.Phegon.Eccormerce.entity.OrderItem;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemSpecificationTest {

    @Mock
    private Root<OrderItem> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Predicate predicate;

    OrderItemSpecificationTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHasStatus_Success() {
        // Arrange
        OrderStatus status = OrderStatus.CONFIRMED;
        when(criteriaBuilder.equal(root.get("status"), status)).thenReturn(predicate);

        // Act
        Predicate result = OrderItemSpecification.hasStatus(status).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).equal(root.get("status"), status);
    }

    @Test
    void testHasStatus_NullStatus() {
        // Act
        Predicate result = OrderItemSpecification.hasStatus(null).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNull(result);
    }

    @Test
    void testCreatedBetween_BothDatesProvided() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);
        LocalDateTime endDate = LocalDateTime.now();
        when(criteriaBuilder.between(root.get("createdAt"), startDate, endDate)).thenReturn(predicate);

        // Act
        Predicate result = OrderItemSpecification.createdBetween(startDate, endDate).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).between(root.get("createdAt"), startDate, endDate);
    }

    @Test
    void testCreatedBetween_OnlyStartDateProvided() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);
        when(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate)).thenReturn(predicate);

        // Act
        Predicate result = OrderItemSpecification.createdBetween(startDate, null).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).greaterThanOrEqualTo(root.get("createdAt"), startDate);
    }

    @Test
    void testCreatedBetween_OnlyEndDateProvided() {
        // Arrange
        LocalDateTime endDate = LocalDateTime.now();
        when(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate)).thenReturn(predicate);

        // Act
        Predicate result = OrderItemSpecification.createdBetween(null, endDate).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).lessThanOrEqualTo(root.get("createdAt"), endDate);
    }

    @Test
    void testCreatedBetween_NullDates() {
        // Act
        Predicate result = OrderItemSpecification.createdBetween(null, null).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNull(result);
    }

    @Test
    void testHasItemId_Success() {
        // Arrange
        Long itemId = 123L;
        when(criteriaBuilder.equal(root.get("id"), itemId)).thenReturn(predicate);

        // Act
        Predicate result = OrderItemSpecification.hasItemId(itemId).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNotNull(result);
        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).equal(root.get("id"), itemId);
    }

    @Test
    void testHasItemId_NullItemId() {
        // Act
        Predicate result = OrderItemSpecification.hasItemId(null).toPredicate(root, query, criteriaBuilder);

        // Assert
        assertNull(result);
    }
}
