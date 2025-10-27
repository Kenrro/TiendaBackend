package com.ecomerce.ecomerce.dto.products;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockResponseDto {
    Long id;
    ProductResponseDto product;
    int stock;
}
