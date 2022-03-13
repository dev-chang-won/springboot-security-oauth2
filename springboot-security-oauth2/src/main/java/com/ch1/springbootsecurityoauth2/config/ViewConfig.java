package com.ch1.springbootsecurityoauth2.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		MustacheViewResolver mustache = new MustacheViewResolver();
		
		mustache.setCharset("UTF-8");
		mustache.setContentType("text/html;charset=UTF-8");
		mustache.setPrefix("classpath:/templates/html/");
		mustache.setSuffix(".html");
		
		registry.viewResolver(mustache);
	}
	
}
