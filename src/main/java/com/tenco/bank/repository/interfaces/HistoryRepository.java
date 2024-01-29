package com.tenco.bank.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tenco.bank.repository.entity.History;


@Mapper
public interface HistoryRepository {
	public int insert(History history);	 // insert 리턴타입 : int 숫자
	public int updateById(History history);	// update 리턴타입 : int 숫자
	public int deleteById(Integer id);	// update 리턴타입 : int 숫자
	
//	계좌 조회
	public History findById(Integer id);
	public List<History> findAll();

}