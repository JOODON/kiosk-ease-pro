package com.example.kioskeasepro.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ValidationDTO<T> {

    private String errorMsg;
    //에러메세지를 담을 errorMsg;

    private T dto;
    //DTO 를 입력해서 맞게 설정

}
