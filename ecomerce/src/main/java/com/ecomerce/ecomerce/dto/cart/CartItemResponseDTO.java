package com.ecomerce.ecomerce.dto.cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponseDTO {
    String productName;
    Long productId;
    Double price;
    int quantity;
    Double subtotal;
}
