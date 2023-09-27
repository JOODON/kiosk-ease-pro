package com.example.kioskeasepro.controller.kioskController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kiosk")
public class KioskViewController {

    @RequestMapping("/create")
    public String createNewMenuFile(){

        return "kiosk_create/kiosk-create-page";
    }

}
