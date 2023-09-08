package com.example.kioskeasepro.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ZipUtilService {
    // 폴더를 압축하고 압축 파일을 생성하는 메서드
    public void zipFolder(String sourceFolderPath, String zipFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath); // 압축 파일을 생성할 FileOutputStream 생성
        ZipOutputStream zipOut = new ZipOutputStream(fos); // ZipOutputStream 생성
        File fileToZip = new File(sourceFolderPath); // 압축할 폴더의 File 객체 생성

        // 압축 파일 생성을 위한 재귀적 메소드 호출
        zipFile(fileToZip, fileToZip.getName(), zipOut);

        zipOut.close(); // ZipOutputStream 닫기
        fos.close(); // FileOutputStream 닫기
    }

    // 폴더 및 파일을 재귀적으로 압축하는 메서드
    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return; // 숨겨진 파일이면 압축하지 않고 종료
        }

        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName)); // 디렉토리를 압축 파일에 추가
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/")); // 디렉토리를 압축 파일에 추가
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut); // 하위 디렉토리 또는 파일을 압축
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip); // 파일을 읽어오기 위한 FileInputStream 생성
        ZipEntry zipEntry = new ZipEntry(fileName); // ZipEntry 생성
        zipOut.putNextEntry(zipEntry); // ZipEntry 를 압축 파일에 추가

        byte[] bytes = new byte[1024];

        int length;

        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length); // 파일 내용을 압축 파일에 쓰기
        }
        fis.close(); // FileInputStream 닫기
    }
    public void downloadZipFile(){
        ZipUtilService zipUtilService = new ZipUtilService();
        String shopName = "CoffeeShop";
        String sourceFolderPath = "C:/SpringBoot/kiosk-ease-pro/src/main/resources/static/" + shopName;
        String zipFilePath = "C:/SpringBoot/kiosk-ease-pro/src/main/resources/static/[" + shopName + "]menuFolder.zip";

        try {
            zipUtilService.zipFolder(sourceFolderPath, zipFilePath);
            System.out.println("폴더가 압축되었습니다: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("압축 중 오류가 발생했습니다.");
        }
    }
}
