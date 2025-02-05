package com.phegondev.Phegon.Eccormerce.entity;

import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private BigDecimal price;
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  // Make sure it's the same class or subclass
        OrderItem that = (OrderItem) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                status == that.status &&
                Objects.equals(user, that.user) &&
                Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, status, user, product, order);
    }

    // canEqual method
    public boolean canEqual(Object other) {
        return other instanceof OrderItem;  // Only OrderItem instances can be compared
    }
}
