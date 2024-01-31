package com.tenco.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bank.dto.AccountSaveFormDto;
import com.tenco.bank.dto.WithdrawFormDto;
import com.tenco.bank.handler.exception.CustomRestfullException;
import com.tenco.bank.repository.entity.Account;
import com.tenco.bank.repository.entity.History;
import com.tenco.bank.repository.interfaces.AccountRepository;
import com.tenco.bank.repository.interfaces.HistoryRepository;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpSession;

@Service // IoC 대상 + 싱글톤으로 관리됨
public class AccountService {

	@Autowired
//	SOLID 원칙 중 OCP 
	private AccountRepository accountRepository;
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private HttpSession httpSession;

//	todo 계좌 번호 중복 확인 예정
	@Transactional
	public void createAccount(AccountSaveFormDto accountSaveFormDto, Integer principalId) {

//		계좌 번호 중복 확인 예외 처리
		if (readAccount(accountSaveFormDto.getNumber()) != null) {
			throw new CustomRestfullException(Define.EXIST_ACCOUNT, HttpStatus.BAD_REQUEST);
		}

		Account account = new Account();
		account.setNumber(accountSaveFormDto.getNumber());
		account.setPassword(accountSaveFormDto.getPassword());
		account.setBalance(accountSaveFormDto.getBalance());
		account.setUserId(principalId);

		int resultRowCount = accountRepository.insert(account);

		if (resultRowCount != 1) {
			throw new CustomRestfullException(Define.FAIL_TO_CREATE_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	단일 계좌 검색 기능
	public Account readAccount(String number) {
		return accountRepository.findByNumber(number.trim());
	}

//	계좌목록 보기 기능
	public List<Account> readAccountListByUserId(Integer principalId) {
		return accountRepository.findAllByUserId(principalId);
	}

//	출금 기능 만들기
//	1. 계좌 존재 여부 확인 -- select
//	2. 본인 계좌 여부 확인 -- select
//	3. 계좌 비번 확인
//	4. 잔액 여부 확인
//	5. 출금 처리 ---> update
//	6. 거래 내역 등록 ---> insert(history 테이블)
//	7. 트랜잭션 처리
	@Transactional
	public void updateAccountWithdraw(WithdrawFormDto dto, Integer principalId) {
//		1.
		Account accountEntity = accountRepository.findByNumber(dto.getWAccountNumber());
		if(accountEntity == null) {
			throw new CustomRestfullException(Define.NOT_EXIST_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		2.
		accountEntity.checkOwner(principalId);
//		3.
		accountEntity.checkPassword(dto.getWAccountPassword());
//		4. 
		accountEntity.checkBalance(dto.getAmount());
//		5. --> 현재 객체 상태값 변경
		accountEntity.withdraw(dto.getAmount());
		accountRepository.updateById(accountEntity);
//		6. 
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWAccountId(accountEntity.getId());
		history.setDAccountId(null);
		history.setWBalance(accountEntity.getBalance());
		history.setDBalance(null);
		
		int rowResultCount = historyRepository.insert(history);
		if(rowResultCount != 1) {
			throw new CustomRestfullException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

} // end of Class
