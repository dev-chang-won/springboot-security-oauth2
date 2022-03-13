package com.ch1.springbootsecurityoauth2.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ch1.springbootsecurityoauth2.model.User;
import com.ch1.springbootsecurityoauth2.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	/* oAuth2 ���� �Ϸ��� ��ó�� ���� 
	 * �ش� �Լ� ����� @AuthenticationPrincipal ������̼� ����
	 * */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("userRequest.getClientRegistration :"+userRequest.getClientRegistration());
		System.out.println("userRequest.getAccessToken :"+userRequest.getAccessToken().getTokenValue());
		
		/*
		 * 1. oauth2 �� �̿��� �α��� �õ�
		 * 2. ���۷α���â
		 * 3. �α��� �Ϸ�
		 * 4. �����ڵ�(��ū) ���� (OAuth-client lib)
		 * 5. ȸ�������� ��û( super.loadUser(userRequest) )
		 * */
		System.out.println("super.loadUser(userRequest) :"+super.loadUser(userRequest).getAttributes());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String provider  = userRequest.getClientRegistration().getClientId();
		String providerId= oAuth2User.getAttribute("sub");
		String username = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode(providerId);		
		String email = oAuth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		// ���� Ȯ�� 
		User user = userRepository.findByUsername(username);
		if( null == user ) {
			user.setProvider(provider);
			user.setProviderId(providerId);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setRole(role);
			
			userRepository.save(user);
		}
		
		//return super.loadUser(userRequest);
		
		//impl �� ������� ��� ��ȯ
		return new PrincipalDetails(user , oAuth2User.getAttributes());
	}

}