package com.example.kioskeasepro.controller.modifyController;


import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class ModifyViewController {

    private final MenuService menuService;
    @RequestMapping("/modify/{id}")
    public String showModifyPage(@PathVariable Long id, Model model){

        MenuDTO menuDTO = menuService.findMenuById(id);
        menuDTO.setImage("/"+menuDTO.getStoreName()+"/menuImage/"+menuDTO.getImage());
        model.addAttribute("menuDTO",menuDTO);

        return "menu_modify/menu-modify";
    }

}
