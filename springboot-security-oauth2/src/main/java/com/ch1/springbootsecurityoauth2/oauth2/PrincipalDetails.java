package com.ch1.springbootsecurityoauth2.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ch1.springbootsecurityoauth2.model.User;

import lombok.Data;


/*
 * 일반 로그인과 OAuth2 로그인 세션을 통합 관리
 * UserDetails , OAuth2User
 * */
@Data
public class PrincipalDetails implements UserDetails , OAuth2User{

	private User user;
	private Map<String,Object> attributes;
	
	//session 로그인
	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}
	//OAuth2 로그인
	public PrincipalDetails(User user , Map<String,Object> attributes) {
		super();
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	// OAuth2User impl을 통해서 추가 Overriding 처리
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attributes.get("sub").toString();
	}	
}
