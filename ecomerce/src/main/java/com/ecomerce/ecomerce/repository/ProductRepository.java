package com.ecomerce.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.ecomerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
