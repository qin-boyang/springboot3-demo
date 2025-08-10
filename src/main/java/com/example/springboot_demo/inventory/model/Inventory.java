// Inventory.java
package com.example.springboot_demo.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    // Constructors
    public Inventory() {}

    public Inventory(Integer quantity, BigDecimal cost) {
        this.quantity = quantity;
        this.cost = cost;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
