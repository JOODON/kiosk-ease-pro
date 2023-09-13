package com.example.kioskeasepro.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
@Service
public class UnzipService {
    public static void main(String[] args) throws IOException {
        unzip("C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\[짜장면집]menuFolder.zip");
    }
    public static void unzip(String zipFilePath) throws IOException {
        String targetDirectory = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static";
        // 버퍼 크기 설정 (1KB)
        byte[] buffer = new byte[1024];

        // Zip 파일 입력 스트림 생성
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Path.of(zipFilePath)))) {
            ZipEntry zipEntry;

            // Zip 파일 내의 각 엔트리(파일 또는 디렉토리)를 반복적으로 처리
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(targetDirectory, entryName);

                // 만약 엔트리가 디렉토리라면 해당 디렉토리 생성
                if (zipEntry.isDirectory()) {
                    entryFile.mkdirs();
                }
                // 엔트리가 파일인 경우
                else {
                    // 파일이 저장될 부모 디렉토리 생성
                    File parentDir = entryFile.getParentFile();
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }

                    // 파일 출력 스트림 생성
                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
