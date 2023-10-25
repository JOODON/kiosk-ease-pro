package com.example.kioskeasepro.controller.menuController;


import com.example.kioskeasepro.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuViewController {

    private final BusinessService businessService;
    @RequestMapping("/")
    public String showMenuListPage(Authentication authentication, Model model){

        String storeName = businessService.findBusinessNameByAuthentication(authentication);

        model.addAttribute("storeName",storeName);

        return "menu_view/menu-view-page";
    }

}
