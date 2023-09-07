package com.example.kioskeasepro.repository;

import com.example.kioskeasepro.entity.MenuDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuDirectoryRepository extends JpaRepository<MenuDirectory,Long> {
    @Query("SELECT MAX(id) FROM MenuDirectory")
    Long findLastId();

}
