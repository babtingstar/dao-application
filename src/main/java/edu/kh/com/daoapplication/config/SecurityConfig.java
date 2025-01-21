package edu.kh.com.daoapplication.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean 을 작성하는 이유
    // 자바에서 만든 PasswordEncoder.java 파일에서 비밀번호 암호화 기능을 사용하겠다 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // 비림번호 암호화 처리하는 자바 기능
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.disable());
        return http.build();
    }
    // SecurityConfig 이외에 다른파일에서 passwordEncoder 를 사용할 수 있도록 설정
}

/**
 * CSRF(Cross-Site Request Forgery)
 * 공격자가 사용자의 권한을 도용해서 악의적인 요청을 서버로 보내도록 유도하는 방식
 * csrf.disable() 비활성화 -> 개발 단개에서 잠시 비활성화 처리해서
 *                              추가적인 암호기능을 개발자가 풀어내기 보다는
 *                              코드 작성에 집중할 수 있도록 설정
 * authorizeHttpRequests
 *  HTTP 요청에 권한을 설정하는 메서드
 *  .anyRequest() = 모든 요청(모든 URL 경로로 들어오는 요청)
 *  .permitAll() = 모두 허용
 *  개발 단계에 있어 코딩이 지장이 생기지 않도록 설정
 *  .formLogin(form -> form.disable());
 *
 *  [build.gradle] 파일
 *  dependencies {
 *      implementation 'org.springframework.boot:spring-boot-starter-security'
 *  }
 *
 *  spring-boot-starter-security 를 dependencies 안에 넣어 사용 활성화를 진행하면
 *  자동으로 보안설정이 되면서 관리자 권한 아이디 비밀번호 작성해야지만 사이트를 이요할 수 있도록 초기에 설정되어 있음
 *
 *  spring-boot-starter-security 에 초기 설정되어 있는 아이디 비밀번호 작성을
 *  form.disable() -> 작성 비활성화 처리함
 */
