package com.example.project_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {
    private Product product;
    private Integer quantity;

    public Double getSubtotal() {
        return product.getPrice() * quantity;
    }
}