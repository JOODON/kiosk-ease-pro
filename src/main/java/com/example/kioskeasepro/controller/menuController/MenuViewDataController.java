package com.example.kioskeasepro.controller.menuController;

import com.example.kioskeasepro.config.auth.PrincipalDetails;
import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.repository.UserRepository;
import com.example.kioskeasepro.service.BusinessService;
import com.example.kioskeasepro.service.MenuService;
import com.example.kioskeasepro.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuViewDataController {

    private static final Logger logger = LoggerFactory.getLogger(MenuViewDataController.class);

    private final MenuService menuService;

    private final BusinessService businessService;

    @RequestMapping(value = "/find-menu-list",method = RequestMethod.GET)
    public ResponseEntity<List<MenuDTO>> presentMenu(Authentication authentication){
        logger.info("present Menu");

        String storeName = businessService.findBusinessNameByAuthentication(authentication);

        List<MenuDTO> menuDTOS = menuService.findMenuByStoreName(storeName);
        //Store 값을 전송하고 그걸 데이터로 받아옴

        System.out.println(menuDTOS);

        return new ResponseEntity<>(menuDTOS, HttpStatus.OK);
    }

}
