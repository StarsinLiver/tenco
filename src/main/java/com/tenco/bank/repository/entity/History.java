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
public class History {
	private Integer id;
	private Long amount;		// 입출금 금액
	private Integer wAccountId;	// 출금 계좌
	private Integer dAccountId;	// 입금 계좌
	private Long wBalance;		// 출금 계좌 잔액
	private Long dBalance;		// 입금 계좌 잔액
	private Timestamp createdAt;
}
