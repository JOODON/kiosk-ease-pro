package com.example.kioskeasepro.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String showKioskEaseMainPage(){


        return "kiosk-ease-main-page";
    }

}
