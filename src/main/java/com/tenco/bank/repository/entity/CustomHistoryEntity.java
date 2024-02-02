package com.tenco.bank.repository.entity;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomHistoryEntity {
	private Integer id;
	private Long amount;
	private Long balance;
	private String sender;
	private String receiver;
	private Timestamp createdAt;
	
//	도전 과제
//	시간 포맷 메서드 만들어 주세요
	
	public String formatCreatedAt() {
		return TimeUtils.timestampToString(createdAt);
	}
	

	public String formatBalance() {
		DecimalFormat df = new DecimalFormat("#,###");
		String formatNumber = df.format(balance);
		return formatNumber + "원";
	}
}