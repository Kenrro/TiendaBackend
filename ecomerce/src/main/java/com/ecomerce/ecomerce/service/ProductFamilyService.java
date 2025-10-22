package com.ecomerce.ecomerce.service;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomerce.ecomerce.dto.families.FamilyRequestDto;
import com.ecomerce.ecomerce.dto.families.FamilyResponseDto;
import com.ecomerce.ecomerce.entity.ProductFamily;
import com.ecomerce.ecomerce.enums.ProductFamilyError;
import com.ecomerce.ecomerce.exception.GeneralEcomerceException;
import com.ecomerce.ecomerce.repository.ProductFamilyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFamilyService {

    final ProductFamilyRepository productFamilyRepository;
    // get product
    public FamilyResponseDto getProductFamilyById(Long id) {
        ProductFamily productFamily = productFamilyRepository.findById(id).orElseThrow(() -> new GeneralEcomerceException(ProductFamilyError.PRODUCT_FAMILY_NOT_FOUND));
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
        try{productFamily = productFamilyRepository.save(productFamily);}
        catch (DataIntegrityViolationException e) {throw new GeneralEcomerceException(ProductFamilyError.CONSTRAINS_VIOLATION, e);}
        catch (Exception e) {throw new GeneralEcomerceException(ProductFamilyError.ERROR_CREATING_PRODUCT_FAMILY, e);}
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
        if (productFamily == null) throw new GeneralEcomerceException(ProductFamilyError.ERROR_UPDATING_PRODUCT_FAMILY);
        return FamilyResponseDto.builder()
            .id(productFamily.getId())
            .name(productFamily.getName())
            .description(productFamily.getDescription())
            .build();
    }
    // Delete product
    @Transactional
    public Boolean deleteProductFamily(Long id) {
        if (productFamilyRepository.deleteProductById(id) == null) throw new GeneralEcomerceException(ProductFamilyError.ERROR_DELETING_PRODUCT_FAMILY);
        return true;
    }
}
