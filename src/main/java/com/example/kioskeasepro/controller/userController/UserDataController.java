package com.example.kioskeasepro.controller.userController;


import com.example.kioskeasepro.dto.UserDTO;
import com.example.kioskeasepro.dto.ValidationDTO;
import com.example.kioskeasepro.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDataController {

    private final UserValidationService userValidationService;
    //register  : 등록하다
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ResponseEntity<String> userRegister(@RequestBody UserDTO userDTO){


        ValidationDTO validationDTO =userValidationService.validateUserInput(userDTO);
        if (validationDTO.getErrorMsg() == null){
            //데이터 베이스에 정상적으로 저장
            return new ResponseEntity<>("회원가입에 성공 하셨습니다.",HttpStatus.OK);
        }
        //유효성 검사 실패 로직
        return new ResponseEntity<>(validationDTO.getErrorMsg(), HttpStatus.BAD_REQUEST);
    }

}
