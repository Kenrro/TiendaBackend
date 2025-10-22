package com.ecomerce.ecomerce.dto.cart;

import com.ecomerce.ecomerce.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDto {
    private Product product;
    private Long cart;
    private int quantity;
}
