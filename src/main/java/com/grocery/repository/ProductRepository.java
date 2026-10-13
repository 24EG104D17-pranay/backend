package com.grocery.repository;

import com.grocery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String search);
    List<Product> findByCategoryIgnoreCase(String category);
}
