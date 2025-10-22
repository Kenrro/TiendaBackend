package com.ecomerce.ecomerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomerce.ecomerce.entity.CartItem;

public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long id);
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
