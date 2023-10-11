package com.example.kioskeasepro.controller.modifyController;


import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ModifyController {

    private final MenuService menuService;
    @PostMapping(value = "/modify-menu")
    public String updateMenu(MenuDTO menuDTO){

        Menu menu = menuService.updateMenuDto(menuDTO.getId(),menuDTO);
        if (menu != null){
            return "redirect:/menu/";

        }
        return "error";
    }
}
