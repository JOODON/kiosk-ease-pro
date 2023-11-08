package com.example.kioskeasepro.controller.postController;

import com.example.kioskeasepro.service.BusinessService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuPostViewController {

    private final BusinessService businessService;
    @RequestMapping("/new")
    public String createNewMenuFile(Authentication authentication, Model model){

        String storeName = businessService.findBusinessNameByAuthentication(authentication);

        model.addAttribute("storeName",storeName);

        return "menu_post/new-menu-post";
    }

}
