package com.ecomerce.ecomerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomerce.ecomerce.dto.cart.CartItemRequestDto;
import com.ecomerce.ecomerce.dto.cart.CartItemResponseDTO;
import com.ecomerce.ecomerce.dto.cart.CartResponseDto;
import com.ecomerce.ecomerce.entity.Cart;
import com.ecomerce.ecomerce.entity.CartItem;
import com.ecomerce.ecomerce.entity.User;
import com.ecomerce.ecomerce.enums.CartError;
import com.ecomerce.ecomerce.exception.GeneralEcomerceException;
import com.ecomerce.ecomerce.repository.CartItemsRepository;
import com.ecomerce.ecomerce.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    // Create crud for cart and cartItems in the same class
    final CartRepository cartRepository;
    final UserService userService;
    final AuthService authService;
    final CartItemsRepository cartItemsRepository;

    
    // get cart
    public CartResponseDto getCart() {
        User user = authService.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(()->
            new GeneralEcomerceException(CartError.CART_NOT_FOUND)
        );
        return CartResponseDto.builder()
            .items(getCartItems(cart.getId()))
            .id(cart.getId())
            .userId(user.getId())
            .created_at(cart.getCreatedAt())
            .build();

    }
    private List<CartItemResponseDTO> getCartItems(Long cartId){
        List<CartItem> items = new ArrayList<>();
        items = cartItemsRepository.findByCartId(cartId);
        return items.stream().map(item -> CartItemResponseDTO.builder()
            .productId(item.getProduct().getId())
            .productName(item.getProduct().getName())
            .price(item.getProduct().getPrice())
            .quantity(item.getQuantity())
            .subtotal(item.getQuantity()*item.getProduct().getPrice())
            .build()
        ).toList();
    }
    // ---------------------
    public CartItemResponseDTO addCartItemToCart(CartItemRequestDto request) {
        CartItem cartItem = cartItemsRepository.findByCartIdAndProductId(request.getCart(), request.getProduct().getId()).orElse(null);
        if (cartItem == null) {
            cartItem = cartItemsRepository.save(
                CartItem.builder()
                    .cart(Cart.builder().id(request.getCart()).build())
                    .product(request.getProduct())
                    .quantity(request.getQuantity())
                    .build()
            );
            if (cartItem == null) throw new GeneralEcomerceException(CartError.PRODUCT_NOT_ADDED_TO_CART);
            return CartItemResponseDTO.builder()
                    .productName(cartItem.getProduct().getName())
                    .productId(cartItem.getProduct().getId())
                    .price(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity())
                    .subtotal(cartItem.getQuantity()*cartItem.getProduct().getPrice())
                    .build();
        }
        cartItem.setQuantity(cartItem.getQuantity()+request.getQuantity());
        cartItem = cartItemsRepository.save(cartItem);
        return CartItemResponseDTO.builder()
                    .productName(cartItem.getProduct().getName())
                    .productId(cartItem.getProduct().getId())
                    .price(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity())
                    .subtotal(cartItem.getQuantity()*cartItem.getProduct().getPrice())
                    .build();
    }
    // Implement Dto and errors for mor information 
    public CartItemResponseDTO removeItemToCart(CartItemRequestDto request) {
        CartItem cartItem = cartItemsRepository.findByCartIdAndProductId(request.getCart(), request.getProduct().getId()).orElseThrow(()->
            new GeneralEcomerceException(CartError.PRODUCT_IS_NOT_IN_THE_CART)
        );
        cartItem.setQuantity(cartItem.getQuantity()-request.getQuantity());
        if (cartItem.getQuantity()<=0) cartItemsRepository.delete(cartItem);
        else cartItem = cartItemsRepository.save(cartItem);
        return CartItemResponseDTO.builder()
                    .productName(cartItem.getProduct().getName())
                    .productId(cartItem.getProduct().getId())
                    .price(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity())
                    .subtotal(cartItem.getQuantity()*cartItem.getProduct().getPrice())
                    .build();
    }

}
