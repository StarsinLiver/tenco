package com.tenco.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.bank.dto.AccountSaveFormDto;
import com.tenco.bank.dto.DepositFormDto;
import com.tenco.bank.dto.TransferFormDto;
import com.tenco.bank.dto.WithdrawFormDto;
import com.tenco.bank.handler.exception.CustomRestfulException;
import com.tenco.bank.repository.entity.Account;
import com.tenco.bank.repository.entity.CustomHistoryEntity;
import com.tenco.bank.repository.entity.History;
import com.tenco.bank.repository.interfaces.AccountRepository;
import com.tenco.bank.repository.interfaces.HistoryRepository;
import com.tenco.bank.utils.Define;

import jakarta.servlet.http.HttpSession;

@Service // IoC의 대상 + 싱글톤으로 관리됨!
public class AccountService {

	// SOLID 원칙 중 - OCP 원칙
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private HistoryRepository historyRepository;

	/**
	 * 단일 계좌 생성
	 * @param dto
	 * @param principalId
	 */
	@Transactional
	public void createAccount(AccountSaveFormDto dto, Integer principalId) {

		// 계좌번호 중복 확인
		if (readAccount(dto.getNumber()) != null) {
			throw new CustomRestfulException(Define.EXIST_ACCOUNT, HttpStatus.BAD_REQUEST);
		}

		Account account = new Account();
		account.setNumber(dto.getNumber());
		account.setPassword(dto.getPassword());
		account.setBalance(dto.getBalance());
		account.setUserId(principalId);

		int resultRowCount = accountRepository.insert(account);
		if (resultRowCount != 1) {
			throw new CustomRestfulException(Define.FAIL_TO_CREATE_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	/**
	 *  단일 계좌 검색 기능
	 * @param number
	 * @return
	 */
	public Account readAccount(String number) {

		return accountRepository.findByNumber(number.trim());
	}

	/**
	 *  계좌 목록 보기 기능
	 * @param principalId
	 * @return
	 */
	public List<Account> readAccountListByUserId(Integer principalId) {
		// select --> 0 이거나 aa, a, ... 예외처리 X
		return accountRepository.findAllByUserId(principalId);
	}

	/**
	 *  단일 계좌 조회 - byId
	 * @param id
	 * @return
	 */
	public Account readByAccountId(Integer id) {
		return accountRepository.findByAccountId(id);
	}

	/**
	 * 출금 기능 만들기
	 * 
	 * @param dto
	 * @param principalId
	 */
	@Transactional
	public void updateAccountWithdraw(WithdrawFormDto dto, Integer principalId) {

		// 1. 계좌 존재 여부 확인
		Account accountEntity = accountRepository.findByNumber(dto.getWAccountNumber());
		if (accountEntity == null) {
			throw new CustomRestfulException(Define.NOT_EXIST_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 2. 본인 계좌여부 확인
		accountEntity.checkOwner(principalId);

		// 3. 계좌 비밀번호 확인
		// String -> equals로 비교
		accountEntity.checkPassword(dto.getWAccountPassword());

		// 4. 잔액 여부 확인
		accountEntity.checkBalance(dto.getAmount());

		// 5. 출금 처리 (Account) --> 현재 생성된 객체 상태값 변경
		accountEntity.withdraw(dto.getAmount());
		accountRepository.updateById(accountEntity);

		// 6. history에 거래내역 등록
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWBalance(accountEntity.getBalance()); // 출금 계좌의 잔액을 가져와야하기 때문에
		history.setDBalance(null);
		history.setWAccountId(accountEntity.getId());
		history.setDAccountId(null);

		int rowResultCount = historyRepository.insert(history);
		if (rowResultCount != 1) {
			throw new CustomRestfulException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 입금 기능 만들기
	 * 
	 * @param dto
	 * @param principalId
	 */
	@Transactional
	public void updateAccountDeposit(DepositFormDto dto, Integer principalId) {
		// 1. 계좌 존재 여부 확인
		Account accountEntity = accountRepository.findByNumber(dto.getDAccountNumber());

		if (accountEntity == null) {
			throw new CustomRestfulException(Define.NOT_EXIST_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 2. 본인 계좌 여부 확인
		accountEntity.checkOwner(principalId);

		// 3. 입금처리
		accountEntity.deposit(dto.getAmount());
		accountRepository.updateById(accountEntity);

		// 6. history에 거래내역 등록
		History history = new History();
		history.setAmount(dto.getAmount());
		history.setWBalance(null); // 출금 계좌의 잔액을 가져와야하기 때문에
		history.setDBalance(accountEntity.getBalance());
		history.setWAccountId(null);
		history.setDAccountId(accountEntity.getId());

		int rowResultCount = historyRepository.insert(history);
		if (rowResultCount != 1) {
			throw new CustomRestfulException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 이체 기능만들기
	 * 
	 * @param dto
	 * @param principalId
	 */
	@Transactional
	public void updateAccountTransfer(TransferFormDto dto, Integer principalId) {
		Account withdrawAccountEntity = accountRepository.findByNumber(dto.getWAccountNumber());
		Account depositAccountEntity = accountRepository.findByNumber(dto.getDAccountNumber());
//		1. 출금 계좌 존재 여부
		if (withdrawAccountEntity == null) {
			throw new CustomRestfulException(Define.NOT_EXIST_ACCOUNT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		2. 입금 계좌 존재 확인
		if (depositAccountEntity == null) {
			throw new CustomRestfulException("상대방의 계좌 번호가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		3. 출금 계좌 본인 소유 확인
		withdrawAccountEntity.checkOwner(principalId);
//		4. 출금 계좌 비번 확인
		withdrawAccountEntity.checkPassword(dto.getPassword());
//		5. 출금 계좌 잔액 확인
		withdrawAccountEntity.checkBalance(dto.getAmount());
//		6. 출금 계좌 잔액 객체 수정
		withdrawAccountEntity.withdraw(dto.getAmount());
//		7. 입금 계좌 잔액 객체 수정
		depositAccountEntity.deposit(dto.getAmount());
//		8. 출금 계좌 update
		int resultRowCountWithdrow = accountRepository.updateById(withdrawAccountEntity);
//		9. 입금 계좌 update
		int resultRowCountDeposit = accountRepository.updateById(depositAccountEntity);
		if (resultRowCountWithdrow != 1 && resultRowCountDeposit != 1) {
			throw new CustomRestfulException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

//		10. 거래 내역 등록 처리
		History history = History.builder().amount(dto.getAmount()) // 이체 금액
				.wAccountId(withdrawAccountEntity.getId()) // 출금 계좌
				.dAccountId(depositAccountEntity.getId()) // 입금 계좌
				.wBalance(withdrawAccountEntity.getBalance()) // 출금 계좌 남은 잔액
				.dBalance(depositAccountEntity.getBalance()) // 입금 계좌 남은 잔액
				.build();
//		11.트랜잭션 처리
		int resultRowCountHistory = historyRepository.insert(history);
		if (resultRowCountHistory != 1) {
			throw new CustomRestfulException("정상 처리 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 단일 계좌 거래 내역 검색 (전체 , 입금 , 출금)
	 * 
	 * @param type = [all , deposit , withdraw]
	 * @param id   (account_id)
	 * @return 동적 쿼리 - List
	 */
	public List<CustomHistoryEntity> readHistoryListByAccount(String type, Integer id) {
		return historyRepository.findByIdHistoryType(type, id);
	}

}