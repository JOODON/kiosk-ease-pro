package com.example.kioskeasepro.service;


import com.example.kioskeasepro.entity.Menu;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KioskFileService {

    public List<Menu> readMenuDataFormFile(String filePath,String storeName) throws IOException {
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
                    String description = data[3];

                    Menu menu = new Menu();

                    menu.setPrice(price);
                    menu.setName(name);
                    menu.setDescription(description);
                    menu.setCategory(category);
                    menu.setStoreName(storeName);

                    fileMenuList.add(menu);
                }
            }
        }

        return fileMenuList;
    }
    public String findStoreName(String originalFileName) {
        // 파일 이름
        // 정규 표현식 패턴
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");

        // 정규 표현식 매치
        Matcher matcher = pattern.matcher(originalFileName);

        if (matcher.find()) {
            String storeName = matcher.group(1);
            System.out.println("가게 이름: " + storeName);
            return storeName;
        } else {
            System.out.println("가게 이름을 찾을 수 없습니다.");
        }
        return "가게 이름을 찾을수 없습니다";
    }


}
