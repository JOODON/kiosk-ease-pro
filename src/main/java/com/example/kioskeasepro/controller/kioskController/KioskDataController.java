package com.example.kioskeasepro.controller.kioskController;


import com.example.kioskeasepro.entity.Create;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.entity.MenuDirectory;
import com.example.kioskeasepro.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@RestController("/kiosk")
@RequiredArgsConstructor
public class KioskDataController {
    private static final Logger logger = LoggerFactory.getLogger(KioskViewController.class);

    private final MenuService menuService;

    private final KioskFileService kioskFileService;

    private final CreateService createService;
    @RequestMapping(value = "/create-kisok",method = RequestMethod.POST)
    public ResponseEntity<String> processZipData(@RequestParam("zipFile")MultipartFile menuZipFile){
        logger.info("Create Kiosk Menu");

        if (!menuZipFile.isEmpty()){
            //알집이 비어 있지 않을 경우에 처리
            try {
                String zipPath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\[가게이름]menuFolder.zip";

                String storeName = kioskFileService.findStoreName(menuZipFile.getOriginalFilename());

                // 문자열 대체 후 새로운 문자열로 할당
                zipPath = zipPath.replace("가게이름", storeName);

                UnzipService.unzip(zipPath);
                //압축된 파일의 압축을 해제

                String filePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\가게이름\\menu\\menu.txt";
                filePath = filePath.replace("가게이름", storeName);

                List<Menu> menuListNoImage = kioskFileService.readMenuDataFormFile(filePath,storeName);
                List<Menu> menuList = menuService.menuEntitySetImagePath(menuListNoImage,storeName);

                menuService.saveMenuList(menuList);

                createService.createMenuData(storeName);

                //생성 데이터 베이스 저장 코드 작성하기

                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>("Failed to process the Zip file.", HttpStatus.INTERNAL_SERVER_ERROR);
                //Zip 파일 설정 x 리턴값으로 서버 에러를 보내줌
            }
        }else {
            return new ResponseEntity<>("No Zip file provided.", HttpStatus.BAD_REQUEST);
            //Zip
        }
    }
}
