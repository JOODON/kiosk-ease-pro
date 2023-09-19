package com.example.kioskeasepro.dto;


import com.example.kioskeasepro.entity.Menu;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Data
public class MenuDTO {

    private Long id;

    private String name;

    private int price;

    private String category;

    private String StoreName;

    private String description;

    private int amount;

    private String image;
    public static MenuDTO convertToMenuDTO(Menu menuEntity){
        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId(menuEntity.getId());
        menuDTO.setName(menuEntity.getName());
        menuDTO.setPrice(menuEntity.getPrice());
        menuDTO.setCategory(menuEntity.getCategory());
        menuDTO.setStoreName(menuEntity.getStoreName());
        menuDTO.setDescription(menuEntity.getDescription());
        menuDTO.setAmount(menuEntity.getAmount());
        menuDTO.setImage(menuEntity.getImagePath());

        return menuDTO;
    }
}
