package com.example.kioskeasepro.config;


import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF(Cross-Site Request Forgery) 보호 및 CORS(Cross-Origin Resource Sharing) 활성화를 비활성화합니다.
        http.csrf().disable().cors().disable()

                // 요청에 대한 권한을 구성합니다.
                .authorizeHttpRequests(request ->
                        // DispatcherType.FORWARD의 요청을 허용합니다. (즉, FORWARD 디스패처 타입의 요청은 모두 허용)
                        request

                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                //URL 관련 설정
                                .requestMatchers("/user/**","/","/api/**").permitAll()

                                //폴더 [static] 폴더 및 하위 폴더들은 접근 허용 관련 설정
                                .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()

                                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 권한을 가진 사용자만 접근 가능
                                // 그 외 모든 요청은 인증(authenticated)이 필요합니다.
                                .anyRequest().authenticated()

                )

                // 폼 로그인(form login)을 구성
                .formLogin(login -> login
                        //로그인 페이지를 대신할 부분 설정
                        .loginPage("/user/login")
                        //로그인을 보낼때 낚아챌 URL 을 설정
                        .loginProcessingUrl("/login") //이부분에서 PrincipalDetails 실행
                        // 로그인 성공 후 리다이렉트될 URL을 설정
                        .defaultSuccessUrl("/", true)
                        // 로그인 페이지는 모든 사용자에게 허용
                        .failureUrl("/user/error")
                        //로그인 실패시 리다이렉트될 URL 설정
                        .permitAll()
                )
                // 로그아웃을 기본 설정으로 구성합니다.
                .logout(Customizer.withDefaults());

        // 구성된 HttpSecurity를 빌드하여 SecurityFilterChain을 반환합니다.
        return http.build();
    }
    //패스워드 암호화를 인한 함수
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
