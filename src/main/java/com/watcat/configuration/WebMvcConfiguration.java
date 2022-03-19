package com.watcat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.watcat.intercepter.adminInterceptor;
import com.watcat.intercepter.normalInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


	
	//일반계정 인터셉터
	@Autowired
	normalInterceptor normalInterceptor;
	
	//관리자계정 인터셉터
	@Autowired
	adminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		 registry.addInterceptor(adminInterceptor)
		 		 .addPathPatterns("/admin/**","/kobis/requestInput" );
		
		 registry.addInterceptor(normalInterceptor)
				.addPathPatterns("/mypage/**");	 
	}
}
