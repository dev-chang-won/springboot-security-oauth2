package com.ch1.springbootsecurityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ch1.springbootsecurityoauth2.oauth2.PrincipalOauth2UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encodeStr() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			/* 자체 구성한 로그인 정보 */
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
		.and()
			/* oauth 사용 인증 정보 */
			.oauth2Login()
			.loginPage("/loginForm")
			/* 완료 후처리 필요 
			 * 1. 정상계정 인증
			 * 2. 엑세스토큰(권한)
			 * 3. provider->scope상의 프로필정보 요청
			 * 4. 서비스 처리( user정보 등록 , 추가정보 확인 , 세션에 등록 )
			 * */
			.userInfoEndpoint()
			/* 인증 완료시 진행할 서비스 등록 */
			.userService(principalOauth2UserService); 
			
	}
}
