package com.grocery.controller;

import com.grocery.model.*;
import com.grocery.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final GroceryOrderRepository orders;
    private final ProductRepository products;
    public OrderController(GroceryOrderRepository orders, ProductRepository products) {
        this.orders = orders; this.products = products;
    }

    @PostMapping
    public GroceryOrder create(@RequestBody OrderRequest request) {
        if (request.customerName == null || request.customerName.isBlank()
                || request.phone == null || request.phone.isBlank()
                || request.address == null || request.address.isBlank()
                || request.items == null || request.items.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer details and at least one item are required");
        }
        GroceryOrder order = new GroceryOrder();
        order.setCustomerName(request.customerName);
        order.setPhone(request.phone);
        order.setAddress(request.address);
        double total = 0;
        for (RequestedItem requested : request.items) {
            if (requested.quantity == null || requested.quantity < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be at least 1");
            }
            Product product = products.findById(requested.productId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
            if (product.getStock() < requested.quantity) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock for " + product.getName());
            }
            product.setStock(product.getStock() - requested.quantity);
            products.save(product);
            order.addItem(new OrderItem(product.getId(), product.getName(), requested.quantity, product.getPrice()));
            total += product.getPrice() * requested.quantity;
        }
        order.setTotal(total);
        return orders.save(order);
    }

    public static class OrderRequest {
        public String customerName;
        public String phone;
        public String address;
        public List<RequestedItem> items;
    }
    public static class RequestedItem {
        public Long productId;
        public Integer quantity;
    }
}
