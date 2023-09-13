package com.example.kioskeasepro.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Menu {
    @Id
    @GeneratedValue // 1씩 자동 증가로 수정
    private Long id;

    private String name;

    private int price;

    private String description;

    private String category;

    private int amount;
    //재고
    private String imagePath;
    //이미지 경로


}
