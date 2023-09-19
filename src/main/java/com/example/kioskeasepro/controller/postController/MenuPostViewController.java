package com.example.kioskeasepro.controller.postController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuPostViewController {

    @RequestMapping("/new")
    public String createNewMenuFile(){

        return "menu_post/new-menu-post";
    }

}
