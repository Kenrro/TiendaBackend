package com.ecomerce.ecomerce.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.ecomerce.dto.products.ProductRequestDto;
import com.ecomerce.ecomerce.dto.products.ProductResponseDto;
import com.ecomerce.ecomerce.dto.products.ProductStockRequestDto;
import com.ecomerce.ecomerce.entity.Product;
import com.ecomerce.ecomerce.entity.ProductFamily;
import com.ecomerce.ecomerce.entity.ProductStock;
import com.ecomerce.ecomerce.enums.ProductError;
import com.ecomerce.ecomerce.exception.GeneralEcomerceException;
import com.ecomerce.ecomerce.repository.ProductRepository;
import com.ecomerce.ecomerce.repository.ProductStockRepository;

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
    // Repair stock
    final ProductRepository productRepository;
    final ProductFamilyService productFamilyService;
    final ProductStockRepository productStockRepository;
    // Create Product
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
            .name(productRequestDto.getName())
            .description(productRequestDto.getDescription())
            .price(productRequestDto.getPrice())
            .productFamily(ProductFamily.builder().id(
                productFamilyService.getProductFamilyById(productRequestDto.getProductFamily()).getId()
            ).build())
            .build();
        try{product = productRepository.save(product);}
        catch (DataIntegrityViolationException e) {throw new GeneralEcomerceException(ProductError.CONSTRAINS_VIOLATION, e);}
        catch (Exception e) {throw new GeneralEcomerceException(ProductError.ERROR_CREATING_PRODUCT, e);}
        // Create stock
        createStock(product);
        return ProductResponseDto.builder()
            .id(productRepository.save(product).getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(0)
            .productFamily(product.getProductFamily().getId())
            .build();
    }
    // Get Product by Id
    public ProductResponseDto getProductById(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new GeneralEcomerceException(ProductError.PRODUCT_NOT_FOUND));
    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stock(product.getStock().getQuantity())
        .productFamily(product.getProductFamily().getId())
        .build();
    }
    public List<ProductResponseDto> getAllProductsFromFamily(Long familyId) {
        productFamilyService.getProductFamilyById(familyId);
        List<Product> products = productRepository.findAllByProductFamily_Id(familyId);
        return products.stream().map(product -> ProductResponseDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .stock(product.getStock().getQuantity())
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
            .stock(product.getStock().getQuantity())
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
            .stock(product.getStock().getQuantity())
            .productFamily(product.getProductFamily().getId())
            .build();
    }
    // Delete Product
    @Transactional
    public boolean deleteProduct(Long id) {
        Product product = productRepository.deleteProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return Objects.nonNull(product);
    }
    // Stock

    private boolean createStock(Product product) {
        ProductStock productStock = ProductStock.builder()
            .product(product)
            .quantity(0)
            .build();
        try {productStock = productStockRepository.save(productStock);}
        catch (DataIntegrityViolationException e) {throw new GeneralEcomerceException(ProductError.ALREDY_EXIST_A_PRODUCT_WITH_THE_SAME_NAME);}
        catch (Exception e) {throw new GeneralEcomerceException(ProductError.ERROR_CREATING_PRODUCT);}
        return productStock != null;
    }
    // 
    @Transactional
    public ProductResponseDto updateStock(ProductStockRequestDto request) {
        ProductStock productStock = productStockRepository.findByProductId(request.getProductId()).orElseThrow(()->
        new GeneralEcomerceException(ProductError.PRODUCT_NOT_FOUND));
        int newStock = productStock.getQuantity();
        if (request.getOperation() == ProductStockRequestDto.Operation.SUM) newStock += request.getQuantity();
        else newStock -= request.getQuantity();
        productStock.setQuantity(newStock >= 0? newStock : 0);
        try{productStock = productStockRepository.save(productStock);}
        catch (Exception e) {throw new GeneralEcomerceException(ProductError.ERROR_UPDATING_PRODUCT);}
        return ProductResponseDto.builder()
                .id(productStock.getProduct().getId())
                .description(productStock.getProduct().getDescription())
                .name(productStock.getProduct().getName())
                .stock(productStock.getQuantity())
                .price(productStock.getProduct().getPrice())
                .productFamily(productStock.getProduct().getProductFamily().getId())
                .build();
            
    }
}
