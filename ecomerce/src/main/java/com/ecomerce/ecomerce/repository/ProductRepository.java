package com.ecomerce.ecomerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.ecomerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> deleteProductById(Long id);

    List<Product> findAllByProductFamily_Id(Long familyId);
}
