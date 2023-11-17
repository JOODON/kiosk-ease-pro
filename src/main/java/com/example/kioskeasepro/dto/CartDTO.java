package com.example.kioskeasepro.dto;

import com.example.kioskeasepro.entity.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class CartDTO {

    private int totalPrice;

    private List<Menu> orderList;

}
