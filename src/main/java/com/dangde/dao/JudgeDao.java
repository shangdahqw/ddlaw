package com.dangde.dao;




import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.dangde.domain.Judge;

public interface JudgeDao {
	
	
	public void insertJudge(Judge judge);
	public Long getmaxkey();		
	public List<Judge> getByCaseId(@Param("caseId")Long caseId);

	
}
