package com.anse.book.springboot.config.auth;

import com.anse.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()    // 1
                .and()
                    .authorizeRequests()    // 2
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()  // 3
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // 4
                    .anyRequest().authenticated()   // 5
                .and()
                    .logout()
                    .logoutSuccessUrl("/")                  // 6
                .and()
                    .oauth2Login()                          // 7
                    .userInfoEndpoint()                     // 8
                    .userService(customOAuth2UserService);  // 9
        /*
         * 1. h2-console화면을 사용하기 위해 해당 옵션들을 disable
         * 2. URL별 권한 관리를 설정하는 옵션의 시작점, 선언되야만 antMatcher를 사용할 수 있다.
         * 3. permitAll()로 전체 권한 줌
         * 4. USER 권한을 가진 사람만 가능
         * 5. 나머지 URL들은 모두 인증된 사용자들에게만 허용(로그인 사용자)
         * 6. 로그아웃 성공시 "/" 주소로 이동
         * 7. Oauth2 로그인 기능에 대한 설정의 진입점
         * 8. Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
         * 9. 소셜로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
         *      리소스 서버(소셜서비스)에서 사용자 정보를 가져온 상태에서
         *      추가로 진행하고 하는 기능을 명시할 수 있음
         *
         */

    }
}
