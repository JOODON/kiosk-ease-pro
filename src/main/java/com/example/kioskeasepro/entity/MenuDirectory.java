package com.example.kioskeasepro.entity;

import com.example.kioskeasepro.dto.MenuDirectoryDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "menu_directory")
@Data
@ToString
public class MenuDirectory {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "STORE-NAME")
    private String storeName;

    @Column(name = "FILE-TEXT")
    private String menuText; // 파일안에 들어갈 이름

    @Column(name = "FILE-PATH")
    private String filePath; // 파일 경로

    @Column(name = "MENU-COUNT")
    private int menuCount; // 메뉴 개수

    public static MenuDirectory convertToMenuDirectory(MenuDirectoryDTO menuDirectoryDTO) {
        MenuDirectory menuDirectory = new MenuDirectory();

        menuDirectory.setStoreName(menuDirectoryDTO.getStoreName());

        menuDirectory.setMenuText(menuDirectoryDTO.getMenuText());

        menuDirectory.setFilePath(menuDirectoryDTO.getFilePath());

        menuDirectory.setMenuCount(menuDirectoryDTO.getMenuCount());

        return menuDirectory;
    }

}
