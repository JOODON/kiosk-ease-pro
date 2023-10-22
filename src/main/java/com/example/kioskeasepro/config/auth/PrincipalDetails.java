package com.example.kioskeasepro.config.auth;

import com.example.kioskeasepro.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    PrincipalDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(user.getRole());
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //계정 만료 처리 True [ 만료 X ] False [ 만료 ]
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //계정 만료 처리 True [ 계정잠금 X ] False [ 계정잠금 ]
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //계정 만료 처리 True [ 패스워드 잠금 X ] False [ 패스워드 잠금 ]
    }

    @Override
    public boolean isEnabled() {
        return true;
        //계정 만료 처리 True [ 계정 활성화 ] False [ 계정 비 활성화 ]
    }
}
