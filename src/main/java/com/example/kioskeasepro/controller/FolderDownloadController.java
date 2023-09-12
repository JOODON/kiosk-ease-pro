    package com.example.kioskeasepro.controller;


    import jakarta.servlet.http.HttpSession;
    import org.springframework.core.io.ByteArrayResource;
    import org.springframework.core.io.Resource;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;


    @RestController
    @RequestMapping("/menu")
    public class FolderDownloadController {
        @GetMapping("/download-folder")
        public ResponseEntity<Resource> downloadFolder(@RequestParam("storeName") String storeName) throws IOException {
            // 압축된 폴더 경로 (압축 파일 경로)
            String zipFilePath = "C:\\SpringBoot\\kiosk-ease-pro\\src\\main\\resources\\static\\["+ storeName +"]menuFolder.zip";

            // 파일을 읽어서 ByteArrayResource 로 변환
            File zipFile = new File(zipFilePath);
            if (zipFile.exists()) {
                byte[] data = Files.readAllBytes(zipFile.toPath());
                ByteArrayResource resource = new ByteArrayResource(data);

                HttpHeaders headers = new HttpHeaders();

                String filename = "[" + storeName + "]menuFolder.zip";
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
                //한글 인코딩 설정
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(data.length)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                // 파일이 존재하지 않는 경우 에러 응답
                return ResponseEntity.notFound().build();
            }
        }
    }
