package com.example.kioskeasepro.menuService;

import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import com.example.kioskeasepro.repository.MenuRepository;
import com.example.kioskeasepro.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuServiceTest {

    private MenuRepository menuRepository;
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        menuRepository = mock(MenuRepository.class); // Mock 객체 생성
        menuService = new MenuService(menuRepository); // MenuService 에 Mock 객체 주입
    }

    @Test
    public void testFindMenuByStoreName() {
        // 테스트 데이터 설정
        String storeName = "ExampleStore";
        Menu menu1 = new Menu();
        menu1.setId(1L);
        menu1.setName("Menu1");
        menu1.setStoreName(storeName);

        Menu menu2 = new Menu();
        menu2.setId(2L);
        menu2.setName("Menu2");
        menu2.setStoreName(storeName);

        when(menuRepository.findAllByStoreName(storeName))
                .thenReturn(List.of(menu1, menu2)); // Mock 객체의 findAllByStoreName 메서드를 설정

        // 테스트 실행
        List<MenuDTO> menuDTOList = menuService.findMenuByStoreName(storeName);

        // 결과 검증
        assertEquals(2, menuDTOList.size());
        assertEquals("Menu1", menuDTOList.get(0).getName());
        assertEquals("Menu2", menuDTOList.get(1).getName());

        // Mock 객체의 메서드 호출 검증
        verify(menuRepository, times(1)).findAllByStoreName(storeName);
    }
}
