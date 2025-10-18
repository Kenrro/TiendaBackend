package com.ecomerce.ecomerce.service;

import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dto.products.ProductRequestDto;
import com.ecomerce.ecomerce.dto.products.ProductResponseDto;
import com.ecomerce.ecomerce.entity.Product;
import com.ecomerce.ecomerce.entity.User;
import com.ecomerce.ecomerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    // TODO: Implement the CRUD operations for products
    // change the id for product family is betther to use a code not a id
    // create at least 3 product families at the start of the application
    // ddl-auto create-drop is activated in application.yml for testing purposes
    // Implement the crud for products families too
    // Create exception handler for all entities
    final ProductRepository productRepository;
    final ProductFamilyService productFamilyService;
    
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
            .name(productRequestDto.getName())
            .description(productRequestDto.getDescription())
            .price(productRequestDto.getPrice())
            .stock(productRequestDto.getCantity())
            .productFamily(productFamilyService.getProductFamilyById(productRequestDto.getProductFamily()))
            .build();
        return ProductResponseDto.builder()
            .id(productRepository.save(product).getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .productFamily(product.getProductFamily().getId())
            .build();
    }
}
