package com.example.kioskeasepro.dto;

import com.example.kioskeasepro.entity.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OrderDTO {

    private Long id;

    private List<Menu> orderItem; //이걸 리스트로 넣는게 맞는건가?

    private String totalPrice;

}
