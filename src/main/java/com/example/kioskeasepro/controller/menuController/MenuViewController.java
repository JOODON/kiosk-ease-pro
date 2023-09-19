package com.example.kioskeasepro.controller.menuController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/menu")
public class MenuViewController {


    @RequestMapping("/")
    public String showMenuListPage(){

        return "menu_view/menu-view-page";
    }

}
