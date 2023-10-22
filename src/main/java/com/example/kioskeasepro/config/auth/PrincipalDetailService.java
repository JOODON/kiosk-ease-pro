package com.example.kioskeasepro.config.auth;

import com.example.kioskeasepro.entity.User;
import com.example.kioskeasepro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);

        User user = userRepository.findByUsername(username);

        if (user != null){
            return new PrincipalDetails(user);
        }

        return null;
    }
}
