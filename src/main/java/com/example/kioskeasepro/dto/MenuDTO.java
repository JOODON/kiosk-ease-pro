package com.example.kioskeasepro.dto;


import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Data
public class MenuDTO {

    private Long id;

    private String name;

    private int price;

    private String category;

    private MultipartFile imageFile; // 이미지 파일을 받을 MultipartFile 객체

}
