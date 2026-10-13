package com.grocery.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grocery_orders")
public class GroceryOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String phone;
    @Column(length = 1000)
    private String address;
    private Double total;
    private String status = "PLACED";
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public GroceryOrder() {}
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public Double getTotal() { return total; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setCustomerName(String v) { customerName = v; }
    public void setPhone(String v) { phone = v; }
    public void setAddress(String v) { address = v; }
    public void setTotal(Double v) { total = v; }
    public void addItem(OrderItem item) { items.add(item); item.setOrder(this); }
}
