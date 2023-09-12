package com.example.kioskeasepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuPostViewController {

    @RequestMapping("/new-post")
    public String createNewMenuFile(){

        return "menu_post/new-menu-post";
    }

}
