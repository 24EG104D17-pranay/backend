package com.grocery.controller;

import com.grocery.model.Product;
import com.grocery.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Product> all(@RequestParam(required = false) String search,
                             @RequestParam(required = false) String category) {
        List<Product> products = (search != null && !search.isBlank())
                ? repository.findByNameContainingIgnoreCase(search) : repository.findAll();
        if (category != null && !category.isBlank() && !category.equalsIgnoreCase("All")) {
            products = products.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
        }
        return products;
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }
}
