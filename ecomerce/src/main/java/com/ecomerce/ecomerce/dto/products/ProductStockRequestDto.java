package com.ecomerce.ecomerce.dto.products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockRequestDto {
    Long productId;
    int quantity;
    Operation operation;

    public enum Operation {
    SUM,
    REST
    }
}


