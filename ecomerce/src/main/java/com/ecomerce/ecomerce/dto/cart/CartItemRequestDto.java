package com.ecomerce.ecomerce.dto.cart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDto {
    @NotNull(message = "Product id is required")
    @Positive(message = "The product id must be more greated than 0")
    private Long productId;

    @NotNull(message = "quantity is required")
    @Min(value = 0, message = "The quantity cannot be negative")
    @Max(value = 10000, message = "The quantity is too large")
    private int quantity;
}
