package com.example.kioskeasepro.service;


import com.example.kioskeasepro.dto.CartDTO;
import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.entity.Order;
import com.example.kioskeasepro.entity.OrderMenu;
import com.example.kioskeasepro.repository.OrderMenuRepository;
import com.example.kioskeasepro.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMenuRepository orderMenuRepository;
    public int saveOrderData(CartDTO cartDTO){

        Order order = new Order();

        OrderMenu orderMenu = new OrderMenu();

        order.setStoreName(cartDTO.getStoreName());

        List<Menu> orderList = new ArrayList<>();

        for (MenuDTO menuDTO : cartDTO.getOrderList()) {
            orderList.add(Menu.convertToMenuEntity(menuDTO));
        }

        orderMenu.setMenus(orderList);

        orderMenu.setOrder(order);

        order.setTotalPrice(cartDTO.getTotalPrice());

        order.setMenuList(orderMenu.getOrder().getMenuList());

        orderRepository.save(order);
        orderMenuRepository.save(orderMenu);


        return 1;

    }

}
