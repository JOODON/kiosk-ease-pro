package com.example.kioskeasepro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString
public class OrderMenu{

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "menu_order_id")
    private List<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
