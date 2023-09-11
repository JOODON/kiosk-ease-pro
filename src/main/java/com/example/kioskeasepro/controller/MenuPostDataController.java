package com.example.kioskeasepro.controller;


import com.example.kioskeasepro.dto.MenuDirectoryDTO;
import com.example.kioskeasepro.service.MenuDirectoryService;
import com.example.kioskeasepro.service.ZipUtilService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<String> processTextData(@RequestBody MenuDirectoryDTO menuDirectoryDTO, HttpSession httpSession) throws IOException {
        logger.info("Processing Text Data");

        //상호명 예정
        String storeName = menuDirectoryDTO.getStoreName();

        httpSession.setAttribute("store",storeName);
        //세션에 값을 저장

        String fileSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+storeName+"\\menu\\menu.txt";

        menuDirectoryService.saveToFile(menuDirectoryDTO.getMenuText(),fileSavePath);

        menuDirectoryDTO.setMenuCount(menuDirectoryService.countMenus(menuDirectoryDTO.getMenuText()));
        menuDirectoryDTO.setFilePath(fileSavePath);

        //세션 값을 저장
        
        menuDirectoryService.saveMenuDirectory(menuDirectoryDTO);
        //DB에 저장 따라서 여기서 시간이 더걸리기 떄문에 이 작업이 끝나면 다운로드

        zipUtilService.downloadZipFile(menuDirectoryDTO.getStoreName());
        //다운로드

        menuDirectoryService.deleteFolderHandler(storeName);
        //파일을 지워주기

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/new-post-file")
    public ResponseEntity<String> processFileData(@RequestParam("files") List<MultipartFile> files,HttpSession httpSession) throws IOException {
        logger.info("Processing file data");
        //로그
        String shopName = (String) httpSession.getAttribute("store");
        //필드 컬럼 가지고 오기

        String imageSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+ shopName +"\\menuImage";
        //파일 이미지 경로를 설정

        menuDirectoryService.saveMultipleImageFiles(files,imageSavePath);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
