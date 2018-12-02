package com.dangde.dao;




import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Court;


public interface CourtDao {
	

	
	public void insertCourt(Court court);
	public Long getmaxkey();		
	public Court getById(@Param("id")Long id);


}
