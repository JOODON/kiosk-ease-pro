package com.example.kioskeasepro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "business")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Business{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Long id;

    @Column(name = "business_Number")
    private String businessId; // 사업자번호

    private String businessOwnerName; //사업주 명

    private String businessName; // 사업장명

    private String businessAddress; // 사업자 주소

}
