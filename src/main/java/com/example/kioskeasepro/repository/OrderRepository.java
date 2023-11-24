package com.example.kioskeasepro.repository;

import com.example.kioskeasepro.entity.Order;
import com.example.kioskeasepro.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByStoreNameAndStatus(String storeName, OrderStatus status);
}
