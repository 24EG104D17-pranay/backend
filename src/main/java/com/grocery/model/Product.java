package com.grocery.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    private String unit;
    private String emoji;
    private Integer stock;

    public Product() {}
    public Product(String name, String category, Double price, String unit, String emoji, Integer stock) {
        this.name = name; this.category = category; this.price = price;
        this.unit = unit; this.emoji = emoji; this.stock = stock;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public String getUnit() { return unit; }
    public String getEmoji() { return emoji; }
    public Integer getStock() { return stock; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(Double price) { this.price = price; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setStock(Integer stock) { this.stock = stock; }
}
