package com.vanphuc.webbanhang.product.repository;

import com.vanphuc.webbanhang.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    Product save(Product product);
}
