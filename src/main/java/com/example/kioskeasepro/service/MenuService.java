package com.example.kioskeasepro.service;

import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    public void saveMenu(Menu menu){

        menuRepository.save(menu);

    }
    public void saveMenuList(List<Menu> fileMenuList){

        for (int i=0; i<fileMenuList.size(); i++){
            saveMenu(fileMenuList.get(i));
        }

    }
    public  List<Menu> readMenuDataFormFile(String filePath) throws IOException {
        List<Menu> fileMenuList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // 예제: txt 형식으로 데이터가 저장된 경우 쉼표를 기준으로 분리하여 MenuEntity 로변환
                String[] data = line.split(" / ");
                if (data.length <= 4) {
                    String name = data[0];
                    int price = Integer.parseInt(data[1]);
                    String category = data[2];
//                    int amount = Integer.parseInt(data[3]);

                    Menu menu = new Menu();

                    menu.setPrice(price);
                    menu.setName(name);
                    menu.setDescription(createDescriptionToMenuName());
//                    menu.setAmount(amount);
                    menu.setCategory(category);

                    fileMenuList.add(menu);
                }
            }
        }

        return fileMenuList;
    }
    private static List<String> getImageFileNames(String directoryPath) {
        List<String> imageFileNames = new ArrayList<>();

        // 디렉토리 객체 생성
        File directory = new File(directoryPath);

        // 디렉토리가 존재하고 디렉토리인지 확인
        if (directory.exists() && directory.isDirectory()) {
            // 디렉토리 내의 모든 파일 목록 가져오기
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // 파일 이름을 리스트에 추가
                        imageFileNames.add(file.getName());
                    }
                }
            }
        }
        return imageFileNames;
    }

    private static String createDescriptionToMenuName() {
        return "인공 지능을 통한 메뉴 설명";
    }

    public List<Menu> menuEntitySetImagePath(List<Menu> menuList,String storeName){
        String directoryPath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\가게이름\\menuImage";
        directoryPath = directoryPath.replace("가게이름",storeName);

        List<String> getImagePathList = getImageFileNames(directoryPath);

        for (int i=0; i<getImagePathList.size(); i++){
            menuList.get(i).setImagePath(getImagePathList.get(i));
            //0번에 리스트를 0번에 선언
        }

        return menuList;
    }



}
