package com.ecomerce.ecomerce.controller.cart;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecomerce.ecomerce.dto.cart.CartItemRequestDto;
import com.ecomerce.ecomerce.dto.cart.CartItemResponseDTO;
import com.ecomerce.ecomerce.dto.cart.CartResponseDto;
import com.ecomerce.ecomerce.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getMethodName() {
        return ResponseEntity.ok(cartService.getCart());
    }
    @PostMapping("/item")
    public ResponseEntity<CartItemResponseDTO> addItemToCart(@Valid @RequestBody CartItemRequestDto entity) {
        return ResponseEntity.ok(cartService.addCartItemToCart(entity));
    }
    @DeleteMapping("/item")
    public ResponseEntity<CartItemResponseDTO> removeItemToCart(@Valid @RequestBody CartItemRequestDto entity){
        return ResponseEntity.ok(cartService.removeItemToCart(entity));
    }
    
    
}
