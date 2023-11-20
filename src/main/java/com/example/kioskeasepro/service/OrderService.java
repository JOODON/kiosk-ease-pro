package com.example.kioskeasepro.service;


import com.example.kioskeasepro.dto.CartDTO;
import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.entity.Order;
import com.example.kioskeasepro.entity.OrderMenu;
import com.example.kioskeasepro.repository.MenuRepository;
import com.example.kioskeasepro.repository.OrderMenuRepository;
import com.example.kioskeasepro.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.control.messages.SimpleMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMenuRepository orderMenuRepository;

    private final MenuRepository menuRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;
    public int saveOrderData(CartDTO cartDTO){

        Order order = new Order();
        //주문 정보 객체 선언
        order.setStoreName(cartDTO.getStoreName());
        order.setTotalPrice(cartDTO.getTotalPrice());

        //주문 정보 필드 설정
        OrderMenu orderMenu = new OrderMenu();
        //주문 메뉴 객체 선언
        List<Menu> orderList = new ArrayList<>();

        for (MenuDTO menuDTO : cartDTO.getOrderList()) {
            Optional<Menu> optionalMenu = menuRepository.findById(menuDTO.getId());

            if (optionalMenu.isPresent()) {
                Menu menu = optionalMenu.get();

                menu.setQuantity(menuDTO.getQuantity());
                // 변경된 메뉴를 저장
                menuRepository.save(menu);

                // OrderList에 메뉴를 추가
                orderList.add(menu);
            }
        }

        orderRepository.save(order);

        orderMenu.setMenus(orderList);
        orderMenu.setOrder(order);
        //주문 메뉴 설정

        orderMenuRepository.save(orderMenu);

        return 1;
    }
    public void sendOrderStatus(CartDTO cartDTO) {
        String receipt = getOrderSummary(cartDTO);
        simpMessagingTemplate.convertAndSend("/menu/", receipt);
    }

    private String getOrderSummary(CartDTO cartDTO) {
        // 주문 정보를 문자열로 합치기
        String storeMessage = "주문 가게: " + cartDTO.getStoreName();
        String menuMessage = "주문 메뉴:\n";
        for (MenuDTO menuDTO : cartDTO.getOrderList()) {
            menuMessage += "- " + menuDTO.getName() + " (" + menuDTO.getQuantity() + "개)\n";
        }
        String totalPriceMessage = "주문 총 가격: " + cartDTO.getTotalPrice() + " 원";

        return storeMessage + "\n" + menuMessage + "\n" + totalPriceMessage;
    }

}
