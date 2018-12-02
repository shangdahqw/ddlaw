package com.dangde.dao.third;

import org.apache.ibatis.annotations.Param;

public interface Case_Trusteeopposition_Dao {
	
	
	
	public void insertCase_Trusteeopposition(@Param("case_id")Long case_id, @Param("trusteeopposition_id")Long Trusteeopposition_id);
	
	
//	public void updateCase_Trusteeopposition(@Param("Case_id")int Case_id, @Param("Trusteeopposition_id") int Trusteeopposition_id);
	

}
