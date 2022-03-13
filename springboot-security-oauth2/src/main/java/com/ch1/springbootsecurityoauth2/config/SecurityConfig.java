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

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encodeStr() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			/* ��ü ������ �α��� ���� */
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
		.and()
			/* oauth ��� ���� ���� */
			.oauth2Login()
			.loginPage("/loginForm")
			/* �Ϸ� ��ó�� �ʿ� 
			 * 1. ������� ����
			 * 2. ��������ū(����)
			 * 3. provider->scope���� ���������� ��û
			 * 4. ���� ó��( user���� ��� , �߰����� Ȯ�� , ���ǿ� ��� )
			 * */
			.userInfoEndpoint()
			/* ���� �Ϸ�� ������ ���� ��� */
			.userService(principalOauth2UserService); 
			
	}
}
