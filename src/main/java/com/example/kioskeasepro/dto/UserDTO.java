package com.example.kioskeasepro.dto;

import com.example.kioskeasepro.entity.User;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username; // 아이디 (이메일)

    private String password; // 비밀번호

    private String name; // 이름

    private String phoneNumber; // 휴대폰번호

    private String gender; // 성별

    private String role; //Role 역할

    private String storeName;

    public static UserDTO convertToUserDTO(User user,String storeName){

        return UserDTO.builder()
                .id(user.getId())
                .password(user.getPassword())
                .username(user.getUsername())
                .name(user.getName())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .storeName(storeName)
        .build();

    }
}