package com.example.kioskeasepro.service;

import com.example.kioskeasepro.dto.UserDTO;

import com.example.kioskeasepro.dto.ValidationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserValidationService implements ValidationService {

    private final BCryptPasswordEncoder passwordEncoder;

    public ValidationDTO validateUserInput(UserDTO userDTO) {
        ValidationDTO validationDTO = new ValidationDTO();

        if (userDTO == null) {
            validationDTO.setUserDTO(null);
            validationDTO.setErrorMsg("데이터가 존재하지 않습니다");
            return validationDTO;
        }

        String errorMsg = null;

        if (!isUsernameValid(userDTO.getUsername())) {
            errorMsg = "이메일 양식이 일치하지 않습니다";
        } else if (!isPasswordValid(userDTO.getPassword())) {
            errorMsg = "비밀번호 양식이 일치하지 않습니다. 비밀번호는 최소 8자, 영문 대/소문자, 숫자, 특수문자(!@#$%^&) 중 최소 하나 포함해야 합니다.";
        } else if (!isBusinessIdValid(userDTO.getBusinessId())) {
            errorMsg = "사업자 번호가 양식에 맞지 않습니다. 숫자로만 이루어진 10자리의 사업자번호를 입력해주세요.";
        } else if (!isNameValid(userDTO.getName())) {
            errorMsg = "이름이 양식에 맞지 않습니다";
        } else if (!isBusinessNameValid(userDTO.getBusinessName())) {
            errorMsg = "사업장 명이 양식에 일치하지 않습니다. 한글, 영문 대/소문자, 숫자, 공백, 특수문자 포함 가능, 2자 이상 50자 이하로 작성해주세요.";
        } else if (!isBusinessAddressValid(userDTO.getBusinessAddress())) {
            errorMsg = "사업장 주소가 양식에 맞지 않습니다. 한글, 영문 대/소문자, 숫자, 공백, 특수문자 포함 가능, 5자 이상 100자 이하를 입력해 주세요.";
        } else if (!isPhoneNumberValid(userDTO.getPhoneNumber())) {
            errorMsg = "휴대폰 번호가 양식에 맞지 않습니다.";
        }else{
            userDTO.setPassword(passwordEncoder(passwordEncoder,userDTO.getPassword()));
        }

        validationDTO.setErrorMsg(errorMsg);
        validationDTO.setUserDTO(userDTO);

        return validationDTO;
    }



    @Override
    public boolean isUsernameValid(String username) {
        // 이메일 주소 유효성 검사 정규식
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        return username.matches(regex);
    }

    @Override
    public boolean isPasswordValid(String password) {
        // 예시: 최소 8자, 영문 대/소문자, 숫자, 특수문자(!@#$%^&) 중 최소 하나 포함
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&]).{8,}$";
        return password.matches(regex);
    }


    @Override
    public boolean isNameValid(String name) {
        // 예시: 한글 또는 영문 대/소문자로만 이루어진 2자 이상 20자 이하의 이름
        String regex = "^[a-zA-Z가-힣]{2,20}$";

        return name.matches(regex);
    }

    @Override
    public boolean isBusinessIdValid(String businessId) {
        // 예시: 숫자로만 이루어진 10자리의 사업자번호
        String regex = "^[0-9]{10}$";
        return businessId.matches(regex);
    }


    @Override
    public boolean isBusinessNameValid(String businessName) {
        // 예시: 한글, 영문 대/소문자, 숫자, 공백, 특수문자 포함 가능, 2자 이상 50자 이하
        String regex = "^[a-zA-Z0-9가-힣\\s\\p{Punct}]{2,50}$";
        return businessName.matches(regex);
    }


    @Override
    public boolean isBusinessAddressValid(String businessAddress) {
        // 예시: 한글, 영문 대/소문자, 숫자, 공백, 특수문자 포함 가능, 5자 이상 100자 이하
        String regex = "^[a-zA-Z0-9가-힣\\s\\p{Punct}]{5,100}$";
        return businessAddress.matches(regex);
    }


    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        // 휴대폰 번호 유효성 검사 정규식 (하이픈 포함)
        String regex = "^01(?:0|1|[6-9])-(\\d{3}-\\d{4}|\\d{4}-\\d{4})$";
        return phoneNumber.matches(regex);
    }



    public String passwordEncoder(PasswordEncoder passwordEncoder, String password){
        return passwordEncoder.encode(password);
    }
}
