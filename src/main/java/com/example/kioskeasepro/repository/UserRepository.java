package com.example.kioskeasepro.repository;

import com.example.kioskeasepro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);

    User findByUsername(String username);

}
