package com.ecomerce.ecomerce.dto.products;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockRequestDto {
    @NotNull(message = "The product id is required.")
    @Positive(message = "The product id must be more greated than 0")
    Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "The quantity cannot be negative")
    @Max(value = 10000, message = "The quantity is too large")
    int quantity;
    
    @NotBlank(message = "The operation is required")
    Operation operation;

    public enum Operation {
    SUM,
    REST
    }
}


