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
		if (amount <= 0) {
			// 잔액은 0이상 이런거 던지기
		}
	}
	
	// 패스워드 체크
	// 잔액 여부 확인 기능
	// 계좌 소유자 확인 기능
}
