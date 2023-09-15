package com.example.kioskeasepro.repository;

import com.example.kioskeasepro.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu> findAllByStoreName(String storeName);
}
