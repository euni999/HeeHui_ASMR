package com.backend.jwt;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity    // 기본적인 Web 보안을 활성화한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {	// 추가적인 설정을 위해 WebSecurityConfigurer를 implements 할 수도 있다

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();     // 에러 403
        http.authorizeRequests()    // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다는 의미
                .antMatchers("/", "/**").permitAll()  // 에러 401
                .anyRequest().authenticated();  // 나머지 요청은 인증이 필요하다
    }
}