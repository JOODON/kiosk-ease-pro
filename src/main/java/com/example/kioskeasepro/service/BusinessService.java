package com.example.kioskeasepro.service;


import com.example.kioskeasepro.dto.BusinessDTO;

import com.example.kioskeasepro.entity.Business;
import com.example.kioskeasepro.entity.User;
import com.example.kioskeasepro.entity.UserBusiness;
import com.example.kioskeasepro.repository.BusinessRepository;
import com.example.kioskeasepro.repository.UserBusinessRepository;
import com.example.kioskeasepro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    private final UserBusinessRepository userBusinessRepository;

    private final UserRepository userRepository;
    public String registerBusiness(BusinessDTO businessDTO){
        try {
            // 사업자 등록 성공한 경우
            Business business = buildEntityFromDTO(businessDTO);

            businessRepository.save(business);

            //사업주 명으로 찾기
            saveUserBusiness(business,findByOwnerName(businessDTO));

            return "정상적으로 회원가입에 성공하셨습니다";

        } catch (DataIntegrityViolationException e) {
            // 데이터베이스 제약 조건 위반 등의 예외 처리

            return "회원가입에 실패하셨습니다. 중복된 데이터 또는 제약 조건 위반입니다.";

        } catch (Exception e) {
            // 그 외 예외 처리
            return "회원가입에 실패하셨습니다. 내부 오류가 발생했습니다.";
        }
    }
    public void saveUserBusiness(Business business, User user) {
        UserBusiness userBusiness = UserBusiness.builder()
                .user(user)
                .business(business)
                .createDate(LocalDateTime.now())
                .build();

        userBusinessRepository.save(userBusiness);
    }
    private Business buildEntityFromDTO(BusinessDTO businessDTO){
        return Business.builder()
                .businessId(businessDTO.getBusinessId())
                .businessOwnerName(businessDTO.getBusinessOwnerName())
                .businessName(businessDTO.getBusinessName())
                .businessAddress(businessDTO.getBusinessAddress())
                .build();
    }

    private User findByOwnerName(BusinessDTO businessDTO){
        return userRepository.findByName(businessDTO.getBusinessOwnerName());
    }
}
