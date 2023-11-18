package com.example.kioskeasepro.entity;


import com.example.kioskeasepro.dto.MenuDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

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

    private String storeName;

    private int quantity;

    public static Menu convertToMenuEntity(MenuDTO menuDTO) {
        Menu menu = new Menu();

        menu.setId(menuDTO.getId());
        menu.setName(menuDTO.getName());
        menu.setPrice(menuDTO.getPrice());
        menu.setDescription(menuDTO.getDescription());
        menu.setCategory(menuDTO.getCategory());
        menu.setAmount(menuDTO.getAmount());
        menu.setImagePath(menuDTO.getImage());
        menu.setStoreName(menuDTO.getStoreName());
        
        //주문 목록이 들어올경우 이부분에서 바꿔주기
        menu.setQuantity(menuDTO.getQuantity());

        return menu;
    }

}
