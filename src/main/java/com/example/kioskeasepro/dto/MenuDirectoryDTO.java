package com.example.kioskeasepro.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Data
public class MenuDirectoryDTO {

    private String text;

    private MultipartFile menuImage;
}
