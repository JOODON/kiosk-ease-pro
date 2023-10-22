package com.example.kioskeasepro.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username; // 아이디 (이메일)

    private String password; // 비밀번호

    private String name; // 이름

    private String phoneNumber; // 휴대폰번호

    private String gender; // 성별
    
    private String role; //유저 등급
}
