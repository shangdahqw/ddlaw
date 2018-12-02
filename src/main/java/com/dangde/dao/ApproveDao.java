package com.dangde.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Approve;

public interface ApproveDao {

	public boolean insertApprove(Approve approve);
	
	public boolean updateFlag(Approve approve);
	
	public List<Approve> getApprove(@Param("material_id")Long material_id);
}
