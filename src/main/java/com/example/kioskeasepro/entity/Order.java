package com.example.kioskeasepro.entity;


import com.example.kioskeasepro.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Data
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private String storeName;

    @OneToMany(mappedBy = "order" )
    private List<OrderMenu> menuList;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
