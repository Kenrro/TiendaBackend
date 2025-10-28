package com.ecomerce.ecomerce.controller.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.ecomerce.dto.families.FamilyRequestDto;
import com.ecomerce.ecomerce.dto.families.FamilyResponseDto;
import com.ecomerce.ecomerce.service.ProductFamilyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class ProductsFamilyController {
    final ProductFamilyService productFamilyService;
    @GetMapping("/{id}")
    public ResponseEntity<FamilyResponseDto> getFamilyById(@PathVariable Long id) {
        return ResponseEntity.ok(productFamilyService.getProductFamilyById(id));
    }
    @PostMapping
    public ResponseEntity<FamilyResponseDto> createFamily(@Valid @RequestBody FamilyRequestDto entity) {
        return ResponseEntity.ok(productFamilyService.createProductFamily(entity));
    }
    @PutMapping("/id")
    public ResponseEntity<FamilyResponseDto> updataFamily(@Valid @RequestBody FamilyRequestDto familyRequestDto, @PathVariable Long id){
        return ResponseEntity.ok(productFamilyService.updateProductFamily(id, familyRequestDto));
    }
    @DeleteMapping("id")
    public ResponseEntity<Boolean> deleteFamily(@PathVariable Long id){
        return ResponseEntity.ok(productFamilyService.deleteProductFamily(id));
    }
    
}
