package com.ecomerce.ecomerce.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long productFamily;
}
