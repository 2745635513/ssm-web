package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Deposit;

public interface DepositService {
	public List<Map<String, Object>> getDepositById(int id, Integer day);

	// 查询时间范围
	public List<Map<String, Object>> getDepositByIdT(int id, @Param("stime") String stime,
			@Param("etime") String etime);

	public int updateDeposit(Deposit deposit);
	
	public List<Deposit> getDeposit(@Param("limit") int limit, @Param("offset") int offset);

	public int getDepositNum();
}
