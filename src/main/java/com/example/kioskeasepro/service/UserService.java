package com.example.kioskeasepro.service;



import com.example.kioskeasepro.dto.UserDTO;
import com.example.kioskeasepro.entity.User;

import com.example.kioskeasepro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BusinessService businessService;
    public String registerUser(UserDTO userDTO) {
        try {
            // 사용자 등록 성공한 경우
            User user = buildEntityFromDTO(userDTO);

            userRepository.save(user);

            return "정상적으로 회원가입에 성공하셨습니다";

        } catch (DataIntegrityViolationException e) {

            // 데이터베이스 제약 조건 위반 등의 예외 처리
            return "회원가입에 실패하셨습니다. 중복된 데이터 또는 제약 조건 위반입니다.";
        } catch (Exception e) {
            // 그 외 예외 처리
            return "회원가입에 실패하셨습니다. 내부 오류가 발생했습니다.";
        }
    }

    private User buildEntityFromDTO(UserDTO userDTO){

        return User.builder()
                .username(userDTO.getUsername())//아이디
                .password(userDTO.getPassword())
                .name(userDTO.getName())//이름
                .phoneNumber(userDTO.getPhoneNumber())
                .gender(userDTO.getGender())
                .role("USER")
                .build();

    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public List<UserDTO>findAllUserList(){
        List<UserDTO> userDTOList = new ArrayList<>();

        List<User> userList = userRepository.findAll();

        int idx = 0;

        for (User user : userList) {
            String storeName = businessService.findByStoreName(userList.get(idx).getName());
            userDTOList.add(UserDTO.convertToUserDTO(user,storeName));
            idx++;
        }

        return userDTOList;
    }

    public UserDTO deleteUser(Long id){

        Optional<User> user = userRepository.findById(id);

        user.ifPresent(userRepository::delete);

        return UserDTO.convertToUserDTO(user.get());

    }

}
