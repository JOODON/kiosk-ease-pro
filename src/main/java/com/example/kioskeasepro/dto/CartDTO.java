package com.example.kioskeasepro.dto;

import com.example.kioskeasepro.entity.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class CartDTO {

    private int totalPrice;

    private String storeName;

    private List<MenuDTO> orderList;
    //메뉴들을 담을 메뉴 리스트
}
