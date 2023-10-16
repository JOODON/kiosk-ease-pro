package com.example.kioskeasepro.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_Business")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_business_id")
    private Long userBusinessId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "business_id", referencedColumnName = "business_id")
    private Business business;

    private LocalDateTime createDate;
}
