package com.dangde.dao;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Operation;


public interface OperationDao {
	

	
	public void insertOperation(Operation Operation);
	public Long getmaxkey();
	public List<Operation> getOperationBycaseId(@Param("caseId")Long caseId);
	public Operation getById(@Param("id")Long id);

}
