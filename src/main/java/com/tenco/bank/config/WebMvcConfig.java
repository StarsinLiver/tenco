package com.tenco.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tenco.bank.handler.AuthInterceptor;

// @Configuration --> 스프링 부트 설정 클래스다
@Configuration // IoC : TIP! 2개 이상에 IoC(Bean 객체를 만들때) 사용
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired // DI
	private AuthInterceptor authInterceptor;

//	요청이 올때마다 domain URI 검사할 예정
//	/account/xxx <- 으로 들어오는 도메인을 다 검사해!!!

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/account/**").addPathPatterns("/auth/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean // IoC - 싱글톤 처리 -- 스프링 컨테이너에 등록
	public PasswordEncoder passwrodEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 리소스 등록 처리
	// 서버 컴퓨터에 위치한 리소스를 활용하는 방법 - 즉 프로젝트 외부 폴더 접근
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		가짜 경로 <-- 
		registry.addResourceHandler("/images/upload/**")
		.addResourceLocations("file:///C:\\work_spring\\upload/"); // 요청에 맵핑될 정적 자원들의 위치 지정 메소드
	}

}
