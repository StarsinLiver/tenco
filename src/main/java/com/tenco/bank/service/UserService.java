package com.tenco.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bank.dto.SignInFormDto;
import com.tenco.bank.dto.SignUpFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.handler.exception.UnAuthorizedException;
import com.tenco.bank.repository.entity.User;
import com.tenco.bank.repository.interfaces.UserRepository;
import com.tenco.bank.utils.Define;

@Service
public class UserService {

//	DB 접근
//	생성자 의존 주입 DI
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 회원가입 로직 처리
	 * @param SignUpFormDto
	 * @return void
	 */
	@Transactional
	public void createUser(SignUpFormDto dto) {
		User user = User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.fullname(dto.getFullname())
				.build();
		int result = userRepository.insert(user);
		if(result != 1) {
			throw new CustomRestfulException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 로그인 처리
	 * @param SignInFormDto
	 * @return User
	 */
	public User readUser(SignInFormDto dto) {
		User user = User.builder().username(dto.getUsername())
				.password(dto.getPassword()).build();
		User userEntity = userRepository.findByUsernameAndPassword(user);
		
		if(userEntity == null) {
			throw new UnAuthorizedException(Define.NOT_AN_AUTHENTICATED_USER,HttpStatus.BAD_REQUEST);
		}
		
		return userEntity;
	}

} // end of Class
