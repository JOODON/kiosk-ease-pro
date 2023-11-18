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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
@RestController("/kiosk")
@RequiredArgsConstructor
public class KioskDataController {
    private static final Logger logger = LoggerFactory.getLogger(KioskViewController.class);

    private final MenuService menuService;

    private final KioskFileService kioskFileService;

    private final CreateService createService;
    @RequestMapping(value = "/create-kiosk", method = RequestMethod.POST)
    public ResponseEntity<String> processZipData(@RequestParam("zipFile") MultipartFile menuZipFile) {
        logger.info("Create Kiosk Menu");

        if (!menuZipFile.isEmpty()) {
            try {
                String zipPath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\[가게이름]menuFolder.zip";
                String storeName = kioskFileService.findStoreName(menuZipFile.getOriginalFilename());

                // 문자열 대체 후 새로운 문자열로 할당
                zipPath = zipPath.replace("가게이름", storeName);

                UnzipService.unzip(zipPath);

                String filePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\가게이름\\menu\\menu.txt";
                filePath = filePath.replace("가게이름", storeName);

                List<Menu> menuListNoImage = kioskFileService.readMenuDataFormFile(filePath, storeName);

                List<Menu> menuList = menuService.menuEntitySetImagePath(menuListNoImage, storeName);

                menuService.saveMenuList(menuList);
                createService.createMenuData(storeName);

                return ResponseEntity.status(HttpStatus.OK).body("디렉토리 제작에 성공하셨습니다.[ 매일 밤 00 :00 ~ 00 : 30  서버 재시작 입니다 그후 사진이 보여질 예정입니다.]");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("키오스크 제작에 실패하였습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일을 첨부하여 다시 진행해주세요.");
        }
    }

}
