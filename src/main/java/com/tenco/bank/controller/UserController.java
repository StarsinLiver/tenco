package com.tenco.bank.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
		System.out.println("dto : " + dto.toString());
		System.out.println(dto.getCustomFile().getOriginalFilename());
		
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
		
		MultipartFile file = dto.getCustomFile();
		
		if(file.isEmpty() == false) {
			// 사용자가 이미지를 업로드 했다면 기능 구현
			// 파일 사이즈 체크
			// 20MB == 1024 * 1024 * 20
			if(file.getSize() > Define.MAX_FILE_SIZE) {
				throw new CustomRestfulException("파일 크기는 20MB 이상 클 수 없습니다.", HttpStatus.BAD_REQUEST);
			}
			
//			서버 컴퓨터에 파일 넣을 디렉토리가 있는지 검사
			String saveDirectory = Define.UPLOAD_FILE_DERECTORY;
//			폴더가 없다면 오류 발생(파일 생성 시)
			File dir = new File(saveDirectory);
			
			if(dir.exists() == false) {
				dir.mkdir(); // 폴더가 없으면 폴더 생성 -- 안해주면 에러 뜸
			}
			
			// 파일 이름 (중복 처리 예방)
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			
			System.out.println("fileName : " + fileName);
			
//			업로드 경로 
			String uploadPath = Define.UPLOAD_FILE_DERECTORY + File.separator + fileName;
			
			File destination = new File(uploadPath);
			try {
				file.transferTo(destination);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			객체 상태 변경
			dto.setOriginFileName(file.getOriginalFilename());
			dto.setUploadFileName(fileName);
			
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
