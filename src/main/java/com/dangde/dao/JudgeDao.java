package com.dangde.dao;

import com.dangde.domain.Judge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JudgeDao {

  public void insertJudge(Judge judge);

  public Long getmaxkey();

  public List<Judge> getByCaseId(@Param("caseId") Long caseId);
}
