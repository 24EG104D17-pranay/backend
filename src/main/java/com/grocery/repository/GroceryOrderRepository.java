package com.grocery.repository;

import com.grocery.model.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryOrderRepository extends JpaRepository<GroceryOrder, Long> {}
