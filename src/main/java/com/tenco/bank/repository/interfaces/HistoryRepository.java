package com.tenco.bank.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.bank.dto.CustomChartDto;
import com.tenco.bank.repository.entity.CustomHistoryEntity;
import com.tenco.bank.repository.entity.History;


@Mapper
public interface HistoryRepository {
	public int insert(History history);
	public int updateById(History history);
	public int deleteById(Integer id);	
	
//	계좌 조회
	public History findById(Integer id);
	public List<History> findAll();
	
	public List<CustomHistoryEntity> findByIdHistoryType(@Param("type") String type , @Param("id")Integer id);

	public List<CustomChartDto> findAllChartMonthly();
}
