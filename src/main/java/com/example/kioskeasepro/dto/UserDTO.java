package com.example.kioskeasepro.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username; // 아이디 (이메일)

    private String password; // 비밀번호

    private String name; // 이름

    private String businessId; // 사업자번호

    private String businessName; // 사업장명

    private String businessAddress; // 사업자 주소

    private String phoneNumber; // 휴대폰번호

    private String gender; // 성별

}
