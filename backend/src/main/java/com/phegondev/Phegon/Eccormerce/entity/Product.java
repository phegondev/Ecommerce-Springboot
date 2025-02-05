package com.phegondev.Phegon.Eccormerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();  // This field will be excluded from equality and hashCode

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  // Using instanceof to allow comparison with subclasses
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imageUrl, product.imageUrl) &&
                Objects.equals(price, product.price) &&
                Objects.equals(category, product.category);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imageUrl, price, category);  // Excluding createdAt from hashCode
    }

    // canEqual method
    public boolean canEqual(Object other) {
        return other instanceof Product;  // Only Product instances or subclasses can be compared
    }
}
