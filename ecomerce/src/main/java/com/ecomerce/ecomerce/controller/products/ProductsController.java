package com.ecomerce.ecomerce.controller.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.ecomerce.dto.products.ProductRequestDto;
import com.ecomerce.ecomerce.dto.products.ProductResponseDto;
import com.ecomerce.ecomerce.dto.products.ProductStockRequestDto;
import com.ecomerce.ecomerce.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {
    // TODO: Do the CRUD operations for products

    final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/family/{familyId}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsFromFamily(@PathVariable Long familyId) {
        return ResponseEntity.ok(productService.getAllProductsFromFamily(familyId));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto request) {
        
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    // Stock
    // TODO: Testing to this endpoint a repair other
    @PutMapping("/stock")
    public ResponseEntity<ProductResponseDto> putMethodName(@Valid @RequestBody ProductStockRequestDto request) {
        return ResponseEntity.ok(productService.updateStock(request));
    }
}
