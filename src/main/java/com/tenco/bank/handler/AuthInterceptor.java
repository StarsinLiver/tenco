package com.tenco.bank.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tenco.bank.handler.exception.UnAuthorizedException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 1. HandlerInterceptor 구현하기
//	AuthInterceptor

@Component // Ioc
public class AuthInterceptor implements HandlerInterceptor {

//	preHandle
//	Controller 로 들어가기 전에 동작 
//	true 를 반환 -> 컨트롤러 실행
//	false를 반환 -> 컨트롤러 미실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		인증 검사
		HttpSession httpSession = request.getSession();
		User principal = (User) httpSession.getAttribute(Define.PRINCIPAL); // 다운 캐스팅

		if (principal == null) {
			response.sendRedirect("/user/sign-in");
//			예외 처리
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.BAD_REQUEST);
		}

		return true;
	}

//	postHandle
//	뷰가 렌더링 되기 전에 호출되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

//	afterCompletion
//	요청 처리가 완료 된 후, 뷰 렌더링이 완료된 후 호출된다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
