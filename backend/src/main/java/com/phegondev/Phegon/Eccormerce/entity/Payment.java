package com.phegondev.Phegon.Eccormerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String method;
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();  // This field will be ignored for equality and hashCode comparisons.

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  // Use instanceof to allow comparison with subclasses
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(amount, payment.amount) &&
                Objects.equals(method, payment.method) &&
                Objects.equals(status, payment.status) &&
                Objects.equals(order, payment.order);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, amount, method, status, order);  // Exclude createdAt from hashCode calculation
    }

    // canEqual method
    public boolean canEqual(Object other) {
        return other instanceof Payment;  // Allow comparison only between Payment instances or its subclasses
    }
}
