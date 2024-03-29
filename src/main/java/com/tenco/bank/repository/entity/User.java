package com.tenco.bank.repository.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Integer id;
	private String username;
	private String password;
	private String fullname;
	private Timestamp createdAt;
	private String originFileName;
	private String uploadFileName;
	
//	사용자가 회웝가입 시, 이미지 넣는 경우 와 이미지를 않넣는 경우
	public String setupUserImage() {
		return uploadFileName == null ? "https://picsum.photos/id/1/350" : "/images/upload/" + uploadFileName;
	}
}
