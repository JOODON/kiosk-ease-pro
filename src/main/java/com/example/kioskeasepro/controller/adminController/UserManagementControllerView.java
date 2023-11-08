package com.example.kioskeasepro.controller.adminController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserManagementControllerView {

    @RequestMapping("/management")
    public String showUserManagementPage(){

        return "admin/user-management";
    }

}
