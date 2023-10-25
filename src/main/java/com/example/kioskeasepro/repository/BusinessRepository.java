package com.example.kioskeasepro.repository;


import com.example.kioskeasepro.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusinessRepository extends JpaRepository<Business,Long> {

    Business findByBusinessOwnerName(String ownerName);
    //비지니스 아이디를 리턴

}
