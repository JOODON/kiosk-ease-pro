package com.example.kioskeasepro.repository;

import com.example.kioskeasepro.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
