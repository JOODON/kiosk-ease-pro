package com.example.kioskeasepro.controller.userController;


import com.example.kioskeasepro.dto.BusinessDTO;
import com.example.kioskeasepro.dto.UserDTO;
import com.example.kioskeasepro.dto.ValidationDTO;
import com.example.kioskeasepro.entity.User;
import com.example.kioskeasepro.service.BusinessService;
import com.example.kioskeasepro.service.UserService;
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

    private final UserService userService;

    private final BusinessService businessService;
    @RequestMapping(value = "/businessRegister",method = RequestMethod.POST)
    public ResponseEntity<String> businessRegister(@RequestBody BusinessDTO businessDTO){

        ValidationDTO<BusinessDTO> businessValidationDTO = userValidationService.validateBusinessInput(businessDTO);

        if (businessValidationDTO.getErrorMsg() == null){
            //데이터 베이스에 정상적으로 저장
            String result = businessService.registerBusiness(businessDTO);

            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        //유효성 검사 실패 로직
        return new ResponseEntity<>(businessValidationDTO.getErrorMsg(), HttpStatus.BAD_REQUEST);
    }

    //register  : 등록하다
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ResponseEntity<String> userRegister(@RequestBody UserDTO userDTO){

        System.out.println(userDTO);

        ValidationDTO<UserDTO> userValidationDTO = userValidationService.validateUserInput(userDTO);

        User existingUser = userService.findByName(userDTO.getName());
        
        if (userValidationDTO.getErrorMsg() == null){
            //중복된 사업자 명이 있을경우에는 재 가입시킴 -> 연관관계 문제 생김
            if (existingUser == null){
                String result = userService.registerUser(userDTO);

                return new ResponseEntity<>(result,HttpStatus.OK);
            }
            //데이터 베이스에 정상적으로 저장
            else {
                userValidationDTO.setErrorMsg(" 중복된 사업자명이 있습니다. 개발자에게 문의해주세요 ");
                new ResponseEntity<>(userValidationDTO.getErrorMsg(), HttpStatus.BAD_REQUEST);
            }
        }
        //유효성 검사 실패 로직
        return new ResponseEntity<>(userValidationDTO.getErrorMsg(), HttpStatus.BAD_REQUEST);
    }

}
