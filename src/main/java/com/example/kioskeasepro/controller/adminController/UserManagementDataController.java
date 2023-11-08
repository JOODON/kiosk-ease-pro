package com.example.kioskeasepro.controller.adminController;


import com.example.kioskeasepro.dto.UserDTO;
import com.example.kioskeasepro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/admin")
@RequiredArgsConstructor
public class UserManagementDataController {

    private final UserService userService;
    @RequestMapping(value = "/userData",method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> presentUser(){
        List<UserDTO> userDTOList = userService.findAllUserList();




        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
}
