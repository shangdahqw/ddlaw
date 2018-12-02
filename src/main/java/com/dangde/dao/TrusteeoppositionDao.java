package com.dangde.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Trusteeopposition;

public interface TrusteeoppositionDao {
	

	
	public void insertTrusteeopposition(Trusteeopposition trusteeopposition);
	public Long getmaxkey();		

	public List<Trusteeopposition> getByCaseId(@Param("caseId")Long caseId);


}
