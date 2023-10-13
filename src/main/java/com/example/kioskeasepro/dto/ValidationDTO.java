package com.example.kioskeasepro.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ValidationDTO {

    private String errorMsg;
    //에러메세지를 담을 errorMsg;

    private UserDTO userDTO;
    //유저 DTO

}
