package com.example.kioskeasepro.controller.modifyController;


import com.example.kioskeasepro.dto.MenuDTO;
import com.example.kioskeasepro.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ModifyController {


    @RequestMapping(value = "/modify-menu",method = RequestMethod.PUT)
    public ResponseEntity<MenuDTO> updateMenu(Long id){
        MenuDTO menuDTO = new MenuDTO();
        //업데이트 로직
        
        return ResponseEntity.ok(menuDTO);
    }
}
