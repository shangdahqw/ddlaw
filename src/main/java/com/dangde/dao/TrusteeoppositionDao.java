package com.dangde.dao;

import com.dangde.domain.Trusteeopposition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrusteeoppositionDao {

  public void insertTrusteeopposition(Trusteeopposition trusteeopposition);

  public Long getmaxkey();

  public List<Trusteeopposition> getByCaseId(@Param("caseId") Long caseId);
}
