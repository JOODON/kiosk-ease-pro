package com.example.kioskeasepro.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@ToString
@Table(name = "CREATE-ENTITY")
public class Create{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String storeName;

    @OneToMany
    @JoinColumn(name = "create_fk_info")
    private List<Menu> menus = new ArrayList<>();
    //MENU TABLE 에 FK 키가 생김 왜?
    //생각하보면 당연함 List DB로 표현 불가 따라서 CreateID를 가진 키우를 지정

    @OneToOne
    @JoinColumn(name = "menuDirecotry_fk_info")
    private MenuDirectory menuDirectory;
    //나한테 FK 키가 생김

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createMenuTime;

}
