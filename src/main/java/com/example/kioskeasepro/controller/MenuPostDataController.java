package com.example.kioskeasepro.controller;


import com.example.kioskeasepro.dto.MenuDirectoryDTO;
import com.example.kioskeasepro.service.MenuDirectoryService;
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

    @RequestMapping(value = "/new-post-text",method = RequestMethod.POST)
    public ResponseEntity<String> processTextData(@RequestBody MenuDirectoryDTO menuDirectoryDTO) throws IOException {
        logger.info("Processing Text Data");
        //로그

        String fileSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\menu\\menu.txt";

        System.out.println(menuDirectoryDTO.getText());

        menuDirectoryService.saveToFile(menuDirectoryDTO.getText(),fileSavePath);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping(value = "/new-post-file")
    public ResponseEntity<String> processFileData(@RequestParam("files") List<MultipartFile> files) throws IOException {
        logger.info("Processing file data");
        //로그
        String imageSavePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\menu\\menuImage";

        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }

        menuDirectoryService.saveMultipleImageFiles(files,imageSavePath);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
