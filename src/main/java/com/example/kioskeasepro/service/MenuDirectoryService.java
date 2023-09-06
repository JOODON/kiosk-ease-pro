package com.example.kioskeasepro.service;


import com.example.kioskeasepro.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MenuDirectoryService {
    
    //텍스트 파일 생성
    public void saveToFile(String data,String filePath) throws IOException {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(data);
        }
    }

    // 여러 이미지 파일을 한꺼번에 처리
    public void saveMultipleImageFiles(List<MultipartFile> imageFiles, String destinationDirectory) throws IOException {

        File directory = new File(destinationDirectory);

        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리가 존재하지 않으면 생성
        }

        for (int i = 0; i < imageFiles.size(); i++) {
            MultipartFile file = imageFiles.get(i);
            String originalFileName = file.getOriginalFilename();

            // 파일 이름 형식을 생성
            String fileName = (i + 1) + "." + originalFileName;

            // 저장할 이미지 파일의 경로를 지정함
            String destinationPath = Paths.get(destinationDirectory, fileName).toString();

            // 이미지 파일을 저장하기 위한 File
            File destinationFile = new File(destinationPath);

            // 이미지 파일 저장
            file.transferTo(destinationFile);
        }
    }

}
