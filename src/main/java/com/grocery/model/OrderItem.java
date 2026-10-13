package com.grocery.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private GroceryOrder order;

    public OrderItem() {}
    public OrderItem(Long productId, String productName, Integer quantity, Double unitPrice) {
        this.productId = productId; this.productName = productName;
        this.quantity = quantity; this.unitPrice = unitPrice;
    }
    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setOrder(GroceryOrder order) { this.order = order; }
}
