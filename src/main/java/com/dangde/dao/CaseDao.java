package com.dangde.dao;




import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.dangde.domain.Case;


public interface CaseDao {
	

	
	public void insertCase(Case Case);
	
	public void updateCase(Case Case);
	
	public Long getmaxkey();		

	public List<Case> findAll(@Param("userId")Long userId);
	public List<Case> findWork(@Param("userId")Long userId,@Param("Now") Date date);

	public Case findByid(@Param("caseId")Long caseId);

}
