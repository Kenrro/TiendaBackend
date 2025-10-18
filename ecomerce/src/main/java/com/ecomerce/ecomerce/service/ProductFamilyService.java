package com.ecomerce.ecomerce.service;

import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.entity.ProductFamily;
import com.ecomerce.ecomerce.repository.ProductFamilyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFamilyService {

    final ProductFamilyRepository productFamilyRepository;
    public ProductFamily getProductFamilyById(Long id) {
        return productFamilyRepository.findById(id).orElseThrow(() -> new RuntimeException("Product family not found"));
    }
}
