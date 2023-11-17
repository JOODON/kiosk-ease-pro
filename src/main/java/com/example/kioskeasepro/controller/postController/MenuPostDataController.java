package com.example.kioskeasepro.controller.postController;


import com.example.kioskeasepro.dto.MenuDirectoryDTO;
import com.example.kioskeasepro.service.BusinessService;
import com.example.kioskeasepro.service.MenuDirectoryService;
import com.example.kioskeasepro.service.ZipUtilService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuPostDataController {
    private static final Logger logger = LoggerFactory.getLogger(MenuPostDataController.class);

    private final MenuDirectoryService menuDirectoryService;

    //파일의 경로 와 파일의 이름을 저장하기 위한 DTO 생성
    private final ZipUtilService zipUtilService;

    private final BusinessService businessService;
    @RequestMapping(value = "/new-post-text",method = RequestMethod.POST)
    public ResponseEntity<String> processTextData(@RequestBody MenuDirectoryDTO menuDirectoryDTO, Authentication authentication) throws IOException {
        logger.info("Processing Text Data");

        String storeName = businessService.findBusinessNameByAuthentication(authentication);
        //접근 권한으로 상호명 가지고오기

        System.out.println(storeName);

        String fileSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+storeName+"\\menu\\menu.txt";

        menuDirectoryService.saveToFile(menuDirectoryDTO.getMenuText(),fileSavePath);
        //파일 데이터를 생성

        menuDirectoryDTO.setMenuCount(menuDirectoryService.countMenus(menuDirectoryDTO.getMenuText()));

        menuDirectoryDTO.setFilePath(fileSavePath);

        menuDirectoryDTO.setStoreName(storeName);

        String resultMessage = menuDirectoryService.saveMenuDirectory(menuDirectoryDTO);
        //DB에 저장 따라서 여기서 시간이 더걸리기 떄문에 이 작업이 끝나면 다운로드

        zipUtilService.downloadZipFile(menuDirectoryDTO.getStoreName());
        //다운로드

        menuDirectoryService.deleteFolderHandler(storeName);
        //파일을 지워주기

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping(value = "/new-post-file")
    public ResponseEntity<String> processMenuData(@RequestParam("files") List<MultipartFile> files, Authentication authentication) throws IOException {
        logger.info("Processing file data");
        //로그
        String storeName = businessService.findBusinessNameByAuthentication(authentication);
        //인증값으로 가게이름 가지고 오기

        //파일 이미지 경로를 설정
        String imageSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\"+ storeName +"\\menuImage";


        menuDirectoryService.saveMultipleImageFiles(files,imageSavePath);
        //이미지 파일이랑 모바일 파일을 둘다 저장

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
}
