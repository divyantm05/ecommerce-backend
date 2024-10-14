package com.divyan.ecommerce.ecommerce_backend.Respository;

import com.divyan.ecommerce.ecommerce_backend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
    List<Product> findByCategory(String category);
}
