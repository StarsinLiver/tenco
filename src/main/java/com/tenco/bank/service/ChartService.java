package com.tenco.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenco.bank.dto.CustomChartDto;
import com.tenco.bank.repository.interfaces.AccountRepository;
import com.tenco.bank.repository.interfaces.HistoryRepository;
import com.tenco.bank.repository.interfaces.UserRepository;

@Service
public class ChartService {

	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * 유저 계정 주간 (일별) 통계
	 * @return List<CustomChartDto>
	 */
	public List<CustomChartDto> FindAllUserChartWeekly() {
		return userRepository.findAllChartWeekly();
	};
	
	/**
	 * 거래 내역 월간 통계
	 * @return List<CustomChartDto>
	 */
	public List<CustomChartDto> findAllHistoryChartMonthly() {
		return historyRepository.findAllChartMonthly();
	};
	
	/**
	 * 계좌 계정 년별 통계
	 * @return List<CustomChartDto>
	 */
	public List<CustomChartDto> findAllAccountChartYearly() {
		return accountRepository.findAllChartYearly();
	};
}
