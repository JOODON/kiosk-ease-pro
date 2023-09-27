package com.example.kioskeasepro.service;

import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<MenuDTO> findMenuByStoreName(String storeName){
        List<MenuDTO> menuDTOList = new ArrayList<>();
        List<Menu> list = menuRepository.findAllByStoreName(storeName);

        for (Menu menu : list) {
            menuDTOList.add(MenuDTO.convertToMenuDTO(menu));
        }

        return menuDTOList;
    }

}
