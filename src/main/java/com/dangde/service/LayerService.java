package com.dangde.service;



import java.util.List;

import com.dangde.domain.Layer;





public interface LayerService {


	public List<Layer> findByCaseId(Long caseId);


}
