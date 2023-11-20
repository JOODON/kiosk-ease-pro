package com.example.kioskeasepro.controller.mobileDataController;


import com.example.kioskeasepro.dto.CartDTO;
import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.dto.OrderDTO;
import com.example.kioskeasepro.entity.Business;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.service.BusinessService;
import com.example.kioskeasepro.service.MenuService;
import com.example.kioskeasepro.service.OrderService;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class MobileDataController {

    private final BusinessService businessService;

    private final MenuService menuService;

    private final OrderService orderService;
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

    @RequestMapping(value = "/save-order-data", method = RequestMethod.POST)
    public ResponseEntity<String> saveOrderData(@RequestBody CartDTO cartDTO) {

        orderService.sendOrderStatus(cartDTO);
        // 주문 정보를 WebSocket 으로 전송
        orderService.saveOrderData(cartDTO);

        return ResponseEntity.ok("주문 접수가 완료 되었습니다.");
    }

}
