package com.ecomerce.ecomerce.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.ecomerce.dto.products.ProductRequestDto;
import com.ecomerce.ecomerce.dto.products.ProductResponseDto;
import com.ecomerce.ecomerce.entity.Product;
import com.ecomerce.ecomerce.entity.ProductFamily;
import com.ecomerce.ecomerce.entity.User;
import com.ecomerce.ecomerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    // 👍 TODO: Implement the CRUD operations for products
    // ❌ change the id for product family is betther to use a code not a id
    //  create at least 3 product families at the start of the application
    // ddl-auto create-drop is activated in application.yml for testing purposes
    // 👍 Implement the crud for products families too
    // 👍 Create exception handler for all entities
    final ProductRepository productRepository;
    final ProductFamilyService productFamilyService;
    // Create Product
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
            .name(productRequestDto.getName())
            .description(productRequestDto.getDescription())
            .price(productRequestDto.getPrice())
            .stock(productRequestDto.getCantity())
            .productFamily(ProductFamily.builder().id(
                productFamilyService.getProductFamilyById(productRequestDto.getProductFamily()).getId()
            ).build())
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
    // Get Product by Id
    public ProductResponseDto getProductById(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock())
        .productFamily(product.getProductFamily().getId())
        .build();
    }
    public List<ProductResponseDto> getAllProductsFromFamily(Long familyId) {
        List<Product> products = productRepository.findAllByProductFamily_Id(familyId);
        return products.stream().map(product -> ProductResponseDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .productFamily(product.getProductFamily().getId())
            .build()).collect(Collectors.toList());
    }
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductResponseDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .productFamily(product.getProductFamily().getId())
            .build()).collect(Collectors.toList());
    }
    // Update Product
    public ProductResponseDto updateProduct(Long id, ProductRequestDto request) {
        Product product = Product.builder()
            .id(id)
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getCantity())
            .productFamily(ProductFamily.builder().id(
                productFamilyService.getProductFamilyById(request.getProductFamily()).getId()
            ).build())
            .build();
        product = productRepository.save(product);
        return ProductResponseDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock())
            .productFamily(product.getProductFamily().getId())
            .build();
    }
    // Delete Product
    @Transactional
    public boolean deleteProduct(Long id) {
        Product product = productRepository.deleteProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return Objects.nonNull(product);
    }
    
}
