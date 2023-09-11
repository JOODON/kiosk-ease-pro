package com.example.kioskeasepro.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MenuDirectoryDTO {

    private Long id;

    //디렉토리 ID
    private String storeName;

    //상호명
    private String menuText;

    //메뉴 텍스트
    private String filePath;

    //파일 저장 위치
    private int menuCount;

    //메뉴 개수
}
