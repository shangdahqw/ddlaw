package com.dangde.dao.third;

import org.apache.ibatis.annotations.Param;

public interface Case_Judge_Dao {
	
	
	
	public void insertCase_Judge(@Param("case_id")Long case_id, @Param("judge_id")Long judge_id);
	
	
//	public void updateCase_Judge(@Param("Case_id")int Case_id, @Param("Judge_id") int Judge_id);
	

}
