package com.ecomerce.ecomerce.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    String name;
    String description;
    Double price;
    int cantity;
    Long productFamily;
}
