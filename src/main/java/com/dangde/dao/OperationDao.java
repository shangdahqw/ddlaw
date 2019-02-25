package com.dangde.dao;

import com.dangde.domain.Operation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationDao {

  public void insertOperation(Operation Operation);

  public Long getmaxkey();

  public List<Operation> getOperationBycaseId(@Param("caseId") Long caseId);

  public Operation getById(@Param("id") Long id);
}
