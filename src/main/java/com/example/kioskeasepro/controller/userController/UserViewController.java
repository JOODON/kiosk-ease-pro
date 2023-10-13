package com.example.kioskeasepro.controller.userController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserViewController {
    
    //유저 회원가입 페이지
    @RequestMapping("/join")
    public String showUserJoinPage(){

        return "user/user-join";
    }
    //유저 로그인 페이지
    @RequestMapping("/login")
    public String shwUserLoginPage(){

        return "user/user-login";
    }

}
