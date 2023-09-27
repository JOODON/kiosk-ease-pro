package com.example.kioskeasepro.service;


import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Create;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.entity.MenuDirectory;
import com.example.kioskeasepro.repository.CreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateService {

    private final CreateRepository createRepository;

    private final MenuDirectoryService menuDirectoryService;

    private final MenuService menuService;
    public void createMenuData(String storeName){
        Create create = new Create();

        create.setStoreName(storeName);

        MenuDirectory menuDirectory = menuDirectoryService.findByStoreName(storeName);

        create.setMenuDirectory(menuDirectory);

        create.setCreateMenuTime(LocalDateTime.now());

        create.setMenus(menuDTOListToMenuEntityList(storeName));

        createRepository.save(create);
    }
    private List<Menu> menuDTOListToMenuEntityList(String storeName){
        List<MenuDTO> menuDTOList = menuService.findMenuByStoreName(storeName);

        List<Menu> menus = new ArrayList<>();

        for (int i=0; i<menuDTOList.size(); i++){
            Menu menu = Menu.convertToMenuEntity(menuDTOList.get(i));
            menus.add(menu);
        }

        return menus;
    }
}
