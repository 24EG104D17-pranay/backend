package com.grocery;

import com.grocery.model.Product;
import com.grocery.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OnlineGroceryStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineGroceryStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner seedProducts(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product("Fresh Apples", "Fruits", 120.0, "1 kg", "🍎", 40));
                repository.save(new Product("Bananas", "Fruits", 50.0, "1 dozen", "🍌", 60));
                repository.save(new Product("Tomatoes", "Vegetables", 35.0, "1 kg", "🍅", 50));
                repository.save(new Product("Potatoes", "Vegetables", 30.0, "1 kg", "🥔", 70));
                repository.save(new Product("Full Cream Milk", "Dairy", 32.0, "500 ml", "🥛", 80));
                repository.save(new Product("Brown Bread", "Bakery", 45.0, "400 g", "🍞", 25));
                repository.save(new Product("Eggs", "Dairy", 75.0, "6 pieces", "🥚", 30));
                repository.save(new Product("Basmati Rice", "Staples", 95.0, "1 kg", "🍚", 45));
                repository.save(new Product("Spinach", "Vegetables", 25.0, "1 bunch", "🥬", 35));
                repository.save(new Product("Orange Juice", "Beverages", 85.0, "1 litre", "🧃", 20));
            }
        };
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "OPTIONS").allowedHeaders("*");
            }
        };
    }
}
