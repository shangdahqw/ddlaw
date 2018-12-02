package com.dangde.dao;




import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.dangde.domain.Layer;


public interface LayerDao {
	

	
	public void insertLayer(Layer layer);
		
	public Long getmaxkey();		

	public List<Layer> getByCaseId(@Param("caseId")Long caseId);


}
