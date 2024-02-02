package com.tenco.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.service.UserService;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

//	was 메모리에 접근하게 해줌
	@Autowired
	private HttpSession httpSession;

	/**
	 * 회원가입 페이지 요청 (/sign-in)
	 * 
	 * @return sign-up 리턴
	 */
	@GetMapping("/sign-up")
	public String signUpPage() {

		return "user/signUp";
	}

//	회원 가입 요청 처리
//	주소 설계 http://localhost:80/user/sign-up
	@PostMapping("/sign-up")
	public String signProc(SignUpFormDto dto) {

//		1. 인증검사 x
		
//		2. 유효성 검사
		if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfulException(Define.ENTER_YOUR_USERNAME, HttpStatus.BAD_REQUEST);
		}

		if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfulException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		if (dto.getFullname() == null || dto.getFullname().isEmpty()) {
			throw new CustomRestfulException(Define.ENTER_YOUR_FULLNAME, HttpStatus.BAD_REQUEST);
		}

		userService.createUser(dto);

		return "redirect:/user/sign-in";
	}

	/**
	 * 로그인 페이지 요청
	 * @return
	 */
	@GetMapping("/sign-in")
	public String signInPage() {
		return "user/signIn";
	}

	/**
	 * 로그인 요청 처리
	 * @param SignInFormDto
	 * @return account/list
	 */
	@PostMapping("/sign-in")
	public String signInProc(SignInFormDto dto) {
//		1. 유효성 검사
		if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new CustomRestfulException(Define.ENTER_YOUR_USERNAME, HttpStatus.BAD_REQUEST);
		}

		if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
			throw new CustomRestfulException(Define.ENTER_YOUR_PASSWORD, HttpStatus.BAD_REQUEST);
		}

//		서비스 호출 예정
		User user = userService.readUser(dto);
//		접근 주체
		httpSession.setAttribute(Define.PRINCIPAL, user);
		
//		로그인 완료 --> 페이지 결정(/account/list)
		return "redirect:/account/list";
	}
	
//	로그아웃 기능
	@GetMapping("/logout")
	public String logout() {
		System.out.print("dsadsa");
		httpSession.invalidate();
		return "redirect:/user/sign-in";
	}

} // end of Class
