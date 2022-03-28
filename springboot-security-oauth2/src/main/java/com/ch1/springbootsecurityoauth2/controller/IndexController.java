package com.ch1.springbootsecurityoauth2.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

	@GetMapping("/")
	public String index () {
		return "index";
	}

	@GetMapping("/loginForm")
	public String loginForm () {
		return "login-form";
	}

	@GetMapping("/user")
	public @ResponseBody String user () {
		return "user";
	}	
	@GetMapping("/manager")
	public @ResponseBody String manager () {
		return "manager";
	}
	@GetMapping("/admin")
	public @ResponseBody String admin () {
		return "admin";
	}	
	
	@GetMapping("/oauthTest")
	public @ResponseBody String oauthTest( Authentication auth , @AuthenticationPrincipal OAuth2User oAuth2 ) {
		System.out.println("===== oauthTest =====");
		
		OAuth2User oAuth2User = (OAuth2User)auth.getPrincipal();
		System.out.println("auth   : "+oAuth2User.getAttributes());
		System.out.println("oAuth2 : "+oAuth2.getAttributes());
		/* auth 의 타입을 Object로 리턴하기에 원하는 형태로 캐스팅 하여 사용 */
		
		return "oauthTest 처리 완료";
	}	
}
