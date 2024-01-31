package com.tenco.bank.repository.entity;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import org.springframework.http.HttpStatus;

import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.utils.Define;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	private Integer id;
	private String number;
	private String password;
	private Long balance;
	private Integer userId;
	private Timestamp createdAt;

	// 출금 기능
	public void withdraw(Long amount) // 메소드 선언부
	{								   // 메소드 바디
		// 방어적 코드 작성 - to do
		if (amount > balance || amount <= 0) {
			// 잔액보다 많다 또는 금액 잘못 적었다. 이런거 던지기
		}
		this.balance -= amount;
	}
	
	// 입금 기능 
	public void deposit(Long amount)
	{
		this.balance += amount;
	}
	
	// 패스워드 체크
	public void checkPassword(String password) {
		if(this.password.equals(password) == false) {
			throw new CustomRestfulException("계좌의 패스워드가 다릅니다.", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	// 잔액 여부 확인 기능
	public void checkBalance(Long amount) {
		if(this.balance < amount) {
			throw new CustomRestfulException("계좌의 잔액이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	// 계좌 소유자 확인 기능
	public void checkOwner(Integer principalId) {
		if(this.userId != principalId) {
			throw new CustomRestfulException("계좌의 사용자가 아닙니다.", HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
//	포메터 기능
	public String formatBalance() {
//		1000 -> 1,000
//		DecimalFormat df = new DecimalFormat("#,###");
//		String formatNumber = df.format(balance);
		String result = balance.toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
		return result + "원";
	}
}
