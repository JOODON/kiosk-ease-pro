package com.example.kioskeasepro.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BusinessDTO {

    private Long id;

    private String businessId; // 사업자번호

    private String businessOwnerName; //사업주 명

    private String businessName; // 사업장명

    private String businessAddress; // 사업자 주소

}
