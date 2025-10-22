package com.ecomerce.ecomerce.dto.cart;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDto {
    Date created_at;
    Long id;
    Long userId;
    List<CartItemResponseDTO> items; 
}
