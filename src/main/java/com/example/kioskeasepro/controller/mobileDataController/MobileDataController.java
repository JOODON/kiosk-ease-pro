package com.example.kioskeasepro.controller.mobileDataController;


import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Business;
import com.example.kioskeasepro.service.BusinessService;
import com.example.kioskeasepro.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class MobileDataController {

    private final BusinessService businessService;

    private final MenuService menuService;
    @RequestMapping(value = "/find-Business-list", method = RequestMethod.GET)
    public ResponseEntity<List<Business>> presentBusinessName() {

        List<Business> businessList = businessService.findAllBusiness();

        return ResponseEntity.ok(businessList);
    }

    @RequestMapping(value = "/find-menu-list", method = RequestMethod.GET)
    public ResponseEntity<List<MenuDTO>> presentMenuName(@RequestParam("storeName") String storeName) {

        List<MenuDTO> menuDTOS = menuService.findMenuByStoreName(storeName);

        return ResponseEntity.ok(menuDTOS);
    }

}
