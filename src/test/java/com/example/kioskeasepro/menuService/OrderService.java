package com.example.kioskeasepro.menuService;


import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrderMenu() {
        // Given
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.setTotalPrice(50.0);
        orderMenu.setDate(new Date());

        User user = new User();
        // set user properties
        user.setId(4L);

        orderMenu.setUser(user);

        Menu menu1 = new Menu();
        // set menu1 properties
        menu1.setId(1L);
        Menu menu2 = new Menu();
        // set menu2 properties
        menu1.setId(2L);
        orderMenu.setOrderMenu(List.of(menu1, menu2));

        // When
        OrderMenu savedOrderMenu = orderRepository.save(orderMenu);

        // Then
        assertNotNull(savedOrderMenu.getId());
        assertEquals(orderMenu.getTotalPrice(), savedOrderMenu.getTotalPrice());
        assertEquals(orderMenu.getDate(), savedOrderMenu.getDate());
        assertEquals(orderMenu.getUser(), savedOrderMenu.getUser());
        assertEquals(orderMenu.getOrderMenu().size(), savedOrderMenu.getOrderMenu().size());
    }

}
