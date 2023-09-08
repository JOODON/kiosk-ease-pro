package com.example.kioskeasepro.controller;


import com.example.kioskeasepro.dto.MenuDirectoryDTO;
import com.example.kioskeasepro.service.MenuDirectoryService;
import com.example.kioskeasepro.service.ZipUtilService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuPostDataController {
    private static final Logger logger = LoggerFactory.getLogger(MenuPostDataController.class);

    private final MenuDirectoryService menuDirectoryService;

    //파일의 경로 와 파일의 이름을 저장하기 위한 DTO 생성
    private final ZipUtilService zipUtilService;
    @RequestMapping(value = "/new-post-text",method = RequestMethod.POST)
    public ResponseEntity<String> processTextData(@RequestBody MenuDirectoryDTO menuDirectoryDTO) throws IOException {
        logger.info("Processing Text Data");

        //상호명 예정
        String shopName = "CoffeeShop";

        String fileSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+shopName+"\\menu\\menu.txt";

        menuDirectoryService.saveToFile(menuDirectoryDTO.getMenuText(),fileSavePath);

        menuDirectoryDTO.setShopName(shopName);
        menuDirectoryDTO.setMenuCount(menuDirectoryService.countMenus(menuDirectoryDTO.getMenuText()));
        menuDirectoryDTO.setFilePath(fileSavePath);

        menuDirectoryService.saveMenuDirectory(menuDirectoryDTO);
        //DB에 저장 따라서 여기서 시간이 더걸림?

        zipUtilService.downloadZipFile();

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/new-post-file")
    public ResponseEntity<String> processFileData(@RequestParam("files") List<MultipartFile> files) throws IOException {
        logger.info("Processing file data");
        //로그

        String shopName = "CoffeeShop";
        //필드 컬럼 가지고 오기

        String imageSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+ shopName +"\\menuImage";
        //파일 이미지 경로를 설정

        menuDirectoryService.saveMultipleImageFiles(files,imageSavePath);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
