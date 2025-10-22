package com.ecomerce.ecomerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.ecomerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long id);
}
