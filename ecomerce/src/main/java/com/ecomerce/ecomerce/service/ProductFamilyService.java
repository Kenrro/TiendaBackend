package com.ecomerce.ecomerce.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.ecomerce.dto.families.FamilyRequestDto;
import com.ecomerce.ecomerce.dto.families.FamilyResponseDto;
import com.ecomerce.ecomerce.entity.ProductFamily;
import com.ecomerce.ecomerce.repository.ProductFamilyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFamilyService {

    final ProductFamilyRepository productFamilyRepository;
    // get product
    public FamilyResponseDto getProductFamilyById(Long id) {
        ProductFamily productFamily = productFamilyRepository.findById(id).orElseThrow(() -> new RuntimeException("Product family not found"));
        return FamilyResponseDto.builder()
            .id(productFamily.getId())
            .name(productFamily.getName())
            .description(productFamily.getDescription())
            .build();
    }
    // Create product
    public FamilyResponseDto createProductFamily(FamilyRequestDto request) {
        ProductFamily productFamily = ProductFamily.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
        productFamily = productFamilyRepository.save(productFamily);
        return FamilyResponseDto.builder()
            .id(productFamily.getId())
            .name(productFamily.getName())
            .description(productFamily.getDescription())
            .build();
    }
    // Update product
    public FamilyResponseDto updateProductFamily(Long id, FamilyRequestDto request){
        ProductFamily productFamily = ProductFamily.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
        productFamily = productFamilyRepository.save(productFamily);
        return FamilyResponseDto.builder()
            .id(productFamily.getId())
            .name(productFamily.getName())
            .description(productFamily.getDescription())
            .build();
    }
    // Delete product
    @Transactional
    public Boolean deleteProductFamily(Long id) {
        return Objects.nonNull(productFamilyRepository.deleteProductById(id));
    }
}
